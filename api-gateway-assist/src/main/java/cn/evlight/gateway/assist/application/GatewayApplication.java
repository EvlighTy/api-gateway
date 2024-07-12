package cn.evlight.gateway.assist.application;

import cn.evlight.gateway.assist.application.config.gateway.GatewayProperties;
import cn.evlight.gateway.assist.domain.model.aggregate.PullAggregate;
import cn.evlight.gateway.assist.domain.model.vo.ApplicationInterfaceMethodEntity;
import cn.evlight.gateway.assist.domain.model.vo.ApplicationInterfaceVO;
import cn.evlight.gateway.assist.domain.model.vo.ApplicationSystemVO;
import cn.evlight.gateway.assist.domain.service.impl.GatewayCenterService;
import cn.evlight.gateway.core.mapping.HTTPStatement;
import cn.evlight.gateway.core.session.Configuration;
import cn.evlight.gateway.core.type.enumeration.HTTPRequestType;
import cn.evlight.gateway.core.type.exception.GatewayException;
import cn.evlight.gateway.core.type.exception.info.ExceptionInfo;
import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;

import java.util.List;

/**
 * @Description: 网关应用
 * @Author: evlight
 * @Date: 2024/7/8
 */
public class GatewayApplication implements ApplicationContextAware, ApplicationListener<ContextClosedEvent> {

    private final Logger logger = LoggerFactory.getLogger(GatewayApplication.class);
    private GatewayProperties properties;
    private GatewayCenterService gatewayCenterService;
    private Configuration configuration;
    private Channel channel;

    public GatewayApplication(GatewayProperties properties, GatewayCenterService gatewayCenterService, Configuration configuration, Channel channel) {
        this.properties = properties;
        this.gatewayCenterService = gatewayCenterService;
        this.configuration = configuration;
        this.channel = channel;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        logger.info("[网关应用]-[初始化] 开始");
        register();
        pull(null);
    }

    public void pull(String systemId) {
        logger.info("[网关应用]-[拉取服务] 开始");
        try {
            PullAggregate pullAggregate = gatewayCenterService.pull(properties.getAddress(),
                    properties.getPullUri(),
                    properties.getGatewayId(),
                    systemId);
            List<ApplicationSystemVO> systemVOS = pullAggregate.getApplicationSystemVOS();
            if (null == systemVOS || systemVOS.isEmpty()) {
                logger.info("[网关应用]-[拉取服务] 网关-系统映射为空 {}", properties.getGatewayId());
                return;
            }
            for (ApplicationSystemVO system : systemVOS) {
                List<ApplicationInterfaceVO> interfaceVOS = system.getApplicationInterfaceVOS();
                if (null == interfaceVOS || interfaceVOS.isEmpty()) {
                    logger.info("[网关应用]-[拉取服务] 系统-接口映射为空 {}", system.getSystemId());
                    continue;
                }
                for (ApplicationInterfaceVO interface_ : interfaceVOS) {
                    List<ApplicationInterfaceMethodEntity> methodVOS = interface_.getApplicationInterfaceMethodVOS();
                    if (null == methodVOS || methodVOS.isEmpty()) {
                        logger.info("[网关应用]-[拉取服务] 系统-接口映射为空 {}", interface_.getInterfaceId());
                        continue;
                    }
                    for (ApplicationInterfaceMethodEntity method : methodVOS) {
                        HTTPStatement httpStatement = new HTTPStatement(system.getSystemId(),
                                interface_.getInterfaceId(),
                                method.getMethodId(),
                                method.getParameterType(),
                                method.getUri(),
                                HTTPRequestType.valueOf(method.getHttpCommandType()),
                                method.getAuth());
                        configuration.addMapper(httpStatement);
                    }
                }
            }
            logger.info("[网关应用]-[拉取服务] 成功");
        } catch (IllegalArgumentException e) {
            logger.info("[网关应用]-[拉取服务] 失败 原因:" + e.getMessage());
            throw new GatewayException(ExceptionInfo.PULL_ERROR);
        }
    }

    public void register() {
        logger.info("[网关应用]-[注册网关] 开始");
        try {
            gatewayCenterService.registerGateway(properties.getAddress(),
                    properties.getRegisterUri(),
                    properties.getGroupId(),
                    properties.getGatewayId(),
                    properties.getGatewayName(),
                    properties.getGatewayAddress());
            logger.info("[网关应用]-[注册网关] 成功");
        } catch (Exception e) {
            logger.error("[网关应用]-[注册网关] 失败 原因:" + e.getMessage());
            throw new GatewayException(ExceptionInfo.INIT_FAILED);
        }
    }

    @Override
    public void onApplicationEvent(ContextClosedEvent contextClosedEvent) {
        logger.info("[网关应用]-[监听容器关闭] 应用容器关闭中");
        try {
            if (channel.isActive()) {
                channel.close();
                logger.info("[网关应用]-[监听容器关闭] 网关已关闭");
            }
        } catch (Exception e) {
            logger.error("[网关应用]-[监听容器关闭] 网关关闭失败 原因:" + e.getMessage());
            throw new GatewayException(ExceptionInfo.CLOSE_FAILED);
        }
    }

}
