package cn.evlight.gateway.sdk.application;

import cn.evlight.gateway.sdk.application.annotation.ProducerClazz;
import cn.evlight.gateway.sdk.application.annotation.ProducerMethod;
import cn.evlight.gateway.sdk.application.config.GatewaySDKProperties;
import cn.evlight.gateway.sdk.domain.model.aggregate.RegisterAggregate;
import cn.evlight.gateway.sdk.domain.model.vo.ApplicationInterfaceVO;
import cn.evlight.gateway.sdk.domain.model.vo.ApplicationInterfaceMethodVO;
import cn.evlight.gateway.sdk.domain.service.impl.GatewayCenterService;
import cn.evlight.gateway.sdk.types.exception.GatewayException;
import cn.evlight.gateway.sdk.types.exception.info.ExceptionInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description: 网关sdk
 * @Author: evlight
 * @Date: 2024/7/9
 */
public class GatewaySDKApplication implements BeanPostProcessor, SmartInitializingSingleton {

    private final Logger logger = LoggerFactory.getLogger(GatewaySDKApplication.class);
    private List<ApplicationInterfaceVO> applicationInterfaceVOS = new ArrayList<>();
    private GatewaySDKProperties gatewaySDKProperties;
    private GatewayCenterService gatewayCenterService;

    public GatewaySDKApplication(GatewaySDKProperties gatewaySDKProperties, GatewayCenterService gatewayCenterService) {
        this.gatewaySDKProperties = gatewaySDKProperties;
        this.gatewayCenterService = gatewayCenterService;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        ProducerClazz producerClazz = bean.getClass().getAnnotation(ProducerClazz.class);
        if (null == producerClazz) {
            return bean;
        }
        logger.info("[服务注册]-[扫描bean] 扫描到需要注册的bean beanName:{}", beanName);
        Class<?>[] interfaces = bean.getClass().getInterfaces();
        if (interfaces.length != 1) {
            throw new GatewayException(ExceptionInfo.CLASS_INTERFACE_NOT_SINGLE);
        }
        String interfaceId = interfaces[0].getName();
        logger.info("[服务注册]-[扫描bean] interfaceId:{}", interfaceId);
        ApplicationInterfaceVO applicationInterfaceVO = new ApplicationInterfaceVO(gatewaySDKProperties.getSystemId(),
                interfaceId,
                producerClazz.interfaceName(),
                producerClazz.version(),
                null);
        ArrayList<ApplicationInterfaceMethodVO> applicationInterfaceMethodVOS = new ArrayList<>();
        Method[] methods = bean.getClass().getMethods();
        for (Method method : methods) {
            ProducerMethod producerMethod = method.getAnnotation(ProducerMethod.class);
            if (null == producerMethod) {
                continue;
            }
            logger.info("[服务注册]-[扫描bean] methodId:{}", method.getName());
            Class<?>[] parameterTypes = method.getParameterTypes();
            List<String> parameterStrTypes = Arrays.stream(parameterTypes)
                    .map(Class::getName)
                    .collect(Collectors.toList());
            String parameterType = String.join(",", parameterStrTypes);
            ApplicationInterfaceMethodVO applicationInterfaceMethodVO = new ApplicationInterfaceMethodVO(gatewaySDKProperties.getSystemId(),
                    interfaceId,
                    method.getName(),
                    producerMethod.methodName(),
                    parameterType,
                    producerMethod.uri(),
                    producerMethod.httpRequestType(),
                    producerMethod.auth());
            applicationInterfaceMethodVOS.add(applicationInterfaceMethodVO);
        }
        applicationInterfaceVO.setApplicationInterfaceMethodVOS(applicationInterfaceMethodVOS);
        applicationInterfaceVOS.add(applicationInterfaceVO);
        return bean;
    }

    @Override
    public void afterSingletonsInstantiated() {
        logger.info("[服务注册]-[服务上报] 开始");
        RegisterAggregate registerAggregate = new RegisterAggregate(gatewaySDKProperties.getSystemId(),
                gatewaySDKProperties.getSystemName(),
                gatewaySDKProperties.getSystemType(),
                gatewaySDKProperties.getSystemRegistry(),
                applicationInterfaceVOS);
        gatewayCenterService.register(gatewaySDKProperties.getCenterAddress(),
                gatewaySDKProperties.getRegisterUri(),
                gatewaySDKProperties.getRegisterNotifyUri(),
                registerAggregate);

    }
}
