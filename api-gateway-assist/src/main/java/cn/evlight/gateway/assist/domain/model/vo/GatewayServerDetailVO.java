package cn.evlight.gateway.assist.domain.model.vo;

/**
 * @Description: 网关详细信息VO
 * @Author: evlight
 * @Date: 2024/7/10
 */
public class GatewayServerDetailVO {
    /**
     * 分组标识
     */
    private String groupId;

    /**
     * 网关标识
     */
    private String gatewayId;

    /**
     * 网关名称
     */
    private String gatewayName;

    /**
     * 网关地址：127.0.0.1
     */
    private String gatewayAddress;

    public GatewayServerDetailVO() {
    }

    public GatewayServerDetailVO(String groupId, String gatewayId, String gatewayName, String gatewayAddress) {
        this.groupId = groupId;
        this.gatewayId = gatewayId;
        this.gatewayName = gatewayName;
        this.gatewayAddress = gatewayAddress;
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
}
