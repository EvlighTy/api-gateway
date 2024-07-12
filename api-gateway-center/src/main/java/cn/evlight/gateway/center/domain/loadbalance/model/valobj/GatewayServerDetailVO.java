package cn.evlight.gateway.center.domain.loadbalance.model.valobj;

/**
 * @Description:
 * @Author: evlight
 * @Date: 2024/7/11
 */
public class GatewayServerDetailVO {

    /**
     * 自增主键
     */
    private Integer id;

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

    /**
     * 服务状态：0不可用、1可使用
     */
    private String status;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
