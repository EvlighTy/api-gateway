package cn.evlight.gateway.assist.application.config.gateway;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Description: 网关配置参数
 * @Author: evlight
 * @Date: 2024/7/8
 */
@ConfigurationProperties("api-gateway.config.gateway")
public class GatewayProperties {

    private String address; //注册中心地址
    private String groupId; //网关组ID
    private String gatewayId; //网关ID
    private String gatewayName; //网关名称
    private String gatewayAddress; //网关地址
    private String registerUri; //注册接口路径
    private String pullUri; //拉取接口路径

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGatewayId() {
        return gatewayId;
    }

    public void setGatewayId(String gatewayId) {
        this.gatewayId = gatewayId;
    }

    public String getGatewayName() {
        return gatewayName;
    }

    public void setGatewayName(String gatewayName) {
        this.gatewayName = gatewayName;
    }

    public String getGatewayAddress() {
        return gatewayAddress;
    }

    public void setGatewayAddress(String gatewayAddress) {
        this.gatewayAddress = gatewayAddress;
    }

    public String getRegisterUri() {
        return registerUri;
    }

    public void setRegisterUri(String registerUri) {
        this.registerUri = registerUri;
    }

    public String getPullUri() {
        return pullUri;
    }

    public void setPullUri(String pullUri) {
        this.pullUri = pullUri;
    }
}
