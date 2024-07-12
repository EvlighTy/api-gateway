package cn.evlight.gateway.sdk.application.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Description: 网关sdk配置参数
 * @Author: evlight
 * @Date: 2024/7/9
 */
@ConfigurationProperties("api-gateway-sdk")
public class GatewaySDKProperties {

    private String centerAddress; //网关中心地址
    private String registerUri; //服务注册接口路径
    private String registerNotifyUri; //服务注册通知接口路径
    private String systemId; //系统ID
    private String systemName; //系统名称
    private String systemType; //服务类型
    private String systemRegistry; //rpc注册中心地址
    private boolean enabled = true; //是否启用

    public String getCenterAddress() {
        return centerAddress;
    }

    public void setCenterAddress(String centerAddress) {
        this.centerAddress = centerAddress;
    }

    public String getRegisterUri() {
        return registerUri;
    }

    public void setRegisterUri(String registerUri) {
        this.registerUri = registerUri;
    }

    public String getRegisterNotifyUri() {
        return registerNotifyUri;
    }

    public void setRegisterNotifyUri(String registerNotifyUri) {
        this.registerNotifyUri = registerNotifyUri;
    }

    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public String getSystemType() {
        return systemType;
    }

    public void setSystemType(String systemType) {
        this.systemType = systemType;
    }

    public String getSystemRegistry() {
        return systemRegistry;
    }

    public void setSystemRegistry(String systemRegistry) {
        this.systemRegistry = systemRegistry;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
