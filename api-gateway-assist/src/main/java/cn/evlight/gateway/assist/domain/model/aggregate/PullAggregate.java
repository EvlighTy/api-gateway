package cn.evlight.gateway.assist.domain.model.aggregate;

import cn.evlight.gateway.assist.domain.model.vo.ApplicationSystemVO;

import java.util.List;

/**
 * @Description: 网关配置信息聚合对象
 * @Author: evlight
 * @Date: 2024/7/8
 */
public class PullAggregate {

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
     * 系统标识
     */
    private String systemId;

    /**
     * 系统名称
     */
    private String systemName;

    private List<ApplicationSystemVO> applicationSystemVOS;

    public PullAggregate() {
    }

    public PullAggregate(Integer id, String groupId, String gatewayId, String systemId, String systemName, List<ApplicationSystemVO> applicationSystemVOS) {
        this.id = id;
        this.groupId = groupId;
        this.gatewayId = gatewayId;
        this.systemId = systemId;
        this.systemName = systemName;
        this.applicationSystemVOS = applicationSystemVOS;
    }

    public PullAggregate(String gatewayId, List<ApplicationSystemVO> applicationSystemVOS) {
        this.gatewayId = gatewayId;
        this.applicationSystemVOS = applicationSystemVOS;
    }

    public PullAggregate(String gatewayId) {
        this.gatewayId = gatewayId;
    }

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

    public List<ApplicationSystemVO> getApplicationSystemVOS() {
        return applicationSystemVOS;
    }

    public void setApplicationSystemVOS(List<ApplicationSystemVO> applicationSystemVOS) {
        this.applicationSystemVOS = applicationSystemVOS;
    }

    public static PullAggregate empty(String gatewayId){
        return new PullAggregate(gatewayId);
    }

}
