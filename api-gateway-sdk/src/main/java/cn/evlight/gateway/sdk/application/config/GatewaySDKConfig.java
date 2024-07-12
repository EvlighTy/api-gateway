package cn.evlight.gateway.sdk.application.config;

import cn.evlight.gateway.sdk.application.GatewaySDKApplication;
import cn.evlight.gateway.sdk.domain.service.impl.GatewayCenterService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: 网关sdk配置
 * @Author: evlight
 * @Date: 2024/7/9
 */
@Configuration
@EnableConfigurationProperties(GatewaySDKProperties.class)
@ConditionalOnProperty(prefix = "api-gateway-sdk", name = "enabled", havingValue = "true", matchIfMissing = true)
public class GatewaySDKConfig {

    @Bean
    public GatewaySDKApplication gatewaySDKApplication(GatewaySDKProperties gatewaySDKProperties, GatewayCenterService gatewayCenterService) {
        return new GatewaySDKApplication(gatewaySDKProperties, gatewayCenterService);
    }

    @Bean
    public GatewayCenterService gatewayCenterService(){
        return new GatewayCenterService();
    }

}
