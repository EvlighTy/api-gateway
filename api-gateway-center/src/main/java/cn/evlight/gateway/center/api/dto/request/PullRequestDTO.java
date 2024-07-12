package cn.evlight.gateway.center.api.dto.request;

/**
 * @Description: 网关配置服务拉取请求体
 * @Author: evlight
 * @Date: 2024/7/8
 */
public class PullRequestDTO {
    private String gatewayId;
    private String systemId;

    public String getGatewayId() {
        return gatewayId;
    }

    public void setGatewayId(String gatewayId) {
        this.gatewayId = gatewayId;
    }

    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }
}
