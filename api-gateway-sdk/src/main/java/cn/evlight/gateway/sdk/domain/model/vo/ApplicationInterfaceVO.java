package cn.evlight.gateway.sdk.domain.model.vo;

import java.util.List;

/**
 * @Description: 接口信息VO
 * @Author: evlight
 * @Date: 2024/7/8
 */
public class ApplicationInterfaceVO {

    /**
     * 系统标识
     */
    private String systemId;

    /**
     * 接口标识
     */
    private String interfaceId;

    /**
     * 接口名称
     */
    private String interfaceName;

    /**
     * 接口版本
     */
    private String interfaceVersion;

    private List<ApplicationInterfaceMethodVO> applicationInterfaceMethodVOS;

    public ApplicationInterfaceVO() {
    }

    public ApplicationInterfaceVO(String systemId, String interfaceId, String interfaceName, String interfaceVersion, List<ApplicationInterfaceMethodVO> applicationInterfaceMethodVOS) {
        this.systemId = systemId;
        this.interfaceId = interfaceId;
        this.interfaceName = interfaceName;
        this.interfaceVersion = interfaceVersion;
        this.applicationInterfaceMethodVOS = applicationInterfaceMethodVOS;
    }

    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }

    public String getInterfaceId() {
        return interfaceId;
    }

    public void setInterfaceId(String interfaceId) {
        this.interfaceId = interfaceId;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public String getInterfaceVersion() {
        return interfaceVersion;
    }

    public void setInterfaceVersion(String interfaceVersion) {
        this.interfaceVersion = interfaceVersion;
    }

    public List<ApplicationInterfaceMethodVO> getApplicationInterfaceMethodVOS() {
        return applicationInterfaceMethodVOS;
    }

    public void setApplicationInterfaceMethodVOS(List<ApplicationInterfaceMethodVO> applicationInterfaceMethodVOS) {
        this.applicationInterfaceMethodVOS = applicationInterfaceMethodVOS;
    }
}
