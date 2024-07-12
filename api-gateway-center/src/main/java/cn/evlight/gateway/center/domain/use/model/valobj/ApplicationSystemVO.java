package cn.evlight.gateway.center.domain.use.model.valobj;

import java.util.List;

/**
 * @Description: 系统信息VO
 * @Author: evlight
 * @Date: 2024/7/8
 */
public class ApplicationSystemVO {

    /**
     * 自增主键
     */
    private Integer id;

    /**
     * 系统标识
     */
    private String systemId;

    /**
     * 系统名称
     */
    private String systemName;

    /**
     * 系统类型；RPC、HTTP
     */
    private String systemType;

    /**
     * 注册中心
     */
    private String systemRegistry;

    private List<ApplicationInterfaceVO> applicationInterfaceVOS;

    public ApplicationSystemVO() {
    }

    public ApplicationSystemVO(Integer id, String systemId, String systemName, String systemType, String systemRegistry, List<ApplicationInterfaceVO> applicationInterfaceVOS) {
        this.id = id;
        this.systemId = systemId;
        this.systemName = systemName;
        this.systemType = systemType;
        this.systemRegistry = systemRegistry;
        this.applicationInterfaceVOS = applicationInterfaceVOS;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public List<ApplicationInterfaceVO> getApplicationInterfaceVOS() {
        return applicationInterfaceVOS;
    }

    public void setApplicationInterfaceVOS(List<ApplicationInterfaceVO> applicationInterfaceVOS) {
        this.applicationInterfaceVOS = applicationInterfaceVOS;
    }
}
