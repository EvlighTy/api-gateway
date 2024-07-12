package cn.evlight.gateway.center.api.dto.request;

import cn.evlight.gateway.center.domain.use.model.valobj.ApplicationInterfaceVO;

import java.util.List;

/**
 * @Description: 服务注册请求体
 * @Author: evlight
 * @Date: 2024/7/10
 */
public class RegisterSystemRequestDTO {

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

    public RegisterSystemRequestDTO() {
    }

    public RegisterSystemRequestDTO(String systemId, String systemName, String systemType, String systemRegistry, List<ApplicationInterfaceVO> applicationInterfaceVOS) {
        this.systemId = systemId;
        this.systemName = systemName;
        this.systemType = systemType;
        this.systemRegistry = systemRegistry;
        this.applicationInterfaceVOS = applicationInterfaceVOS;
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
