package cn.evlight.gateway.assist.application.config.gateway;

import cn.evlight.gateway.assist.application.GatewayApplication;
import cn.evlight.gateway.assist.domain.service.impl.GatewayCenterService;
import cn.evlight.gateway.assist.trigger.listener.RegisterServiceListener;
import cn.evlight.gateway.assist.types.enumeration.RedisTopic;
import cn.evlight.gateway.core.session.defaults.DefaultGatewaySessionFactory;
import cn.evlight.gateway.core.socket.GatewaySocketServer;
import cn.evlight.gateway.core.type.Characters;
import cn.evlight.gateway.core.type.exception.GatewayException;
import cn.evlight.gateway.core.type.exception.info.ExceptionInfo;
import io.netty.channel.Channel;
import org.redisson.api.RTopic;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @Description: 网关配置
 * @Author: evlight
 * @Date: 2024/7/8
 */
@Configuration
@EnableConfigurationProperties(GatewayProperties.class)
public class GatewayConfig {

    private Logger logger = LoggerFactory.getLogger(GatewayConfig.class);

    //配置项
    @Bean
    public cn.evlight.gateway.core.session.Configuration configuration(GatewayProperties gatewayProperties) {
        String[] split = gatewayProperties.getGatewayAddress().split(":");
        if (split.length != 2) {
            return new cn.evlight.gateway.core.session.Configuration();
        }
        return new cn.evlight.gateway.core.session.Configuration(split[0].trim(), Integer.parseInt(split[1].trim()));
    }

    //网关服务
    @Bean
    public Channel startGateway(cn.evlight.gateway.core.session.Configuration configuration) throws Exception {
        logger.info("[启动网关] 开始");
        DefaultGatewaySessionFactory sessionFactory = new DefaultGatewaySessionFactory(configuration);
        GatewaySocketServer gatewaySocketServer = new GatewaySocketServer(configuration, sessionFactory);
        Future<Channel> future = Executors.newFixedThreadPool(2).submit(gatewaySocketServer);
        Channel channel = future.get();
        if (null == channel) {
            throw new GatewayException(ExceptionInfo.START_FAILED + ":" + "channel is null");
        }
        while (!channel.isActive()) {
            logger.info("通道初始化中...");
        }
        logger.info("[启动网关] 成功");
        return channel;
    }

    //网关应用
    @Bean
    public GatewayApplication gatewayApplication(GatewayProperties gatewayProperties, GatewayCenterService gatewayCenterService, cn.evlight.gateway.core.session.Configuration configuration, Channel gatewaySocketServerChannel) {
        return new GatewayApplication(gatewayProperties, gatewayCenterService, configuration, gatewaySocketServerChannel);
    }

    //网关中心服务
    @Bean
    public GatewayCenterService gatewayCenterService() {
        return new GatewayCenterService();
    }

    //系统注册监听器
    @Bean
    public RegisterServiceListener registerServiceListener(GatewayApplication gatewayApplication) {
        return new RegisterServiceListener(gatewayApplication);
    }

    //消息队列
    @Bean
    public RTopic GatewayRedisTopic(RedissonClient redissonClient, RegisterServiceListener registerServiceListener, GatewayProperties gatewayProperties) {
        String topicKey = RedisTopic.REGISTER_SYSTEM_TOPIC.getKey() + Characters.COLON + gatewayProperties.getGatewayId();
        RTopic topic = redissonClient.getTopic(topicKey);
        topic.addListener(String.class, registerServiceListener);
        return topic;
    }
}
