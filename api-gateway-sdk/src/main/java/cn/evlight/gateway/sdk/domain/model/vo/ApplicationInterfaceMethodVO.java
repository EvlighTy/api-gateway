package cn.evlight.gateway.sdk.domain.model.vo;

/**
 * @Description: 方法信息
 * @Author: evlight
 * @Date: 2024/7/8
 */
public class ApplicationInterfaceMethodVO {

    /**
     * 系统标识
     */
    private String systemId;

    /**
     * 接口标识
     */
    private String interfaceId;

    /**
     * 方法标识
     */
    private String methodId;

    /**
     * 方法名称
     */
    private String methodName;

    /**
     * 参数类型；(RPC 限定单参数注册)；new String[]{"java.lang.String"}、new String[]{"cn.bugstack.gateway.rpc.dto.XReq"}
     */
    private String parameterType;

    /**
     * 网关接口
     */
    private String uri;

    /**
     * 接口类型；GET、POST、PUT、DELETE
     */
    private String httpCommandType;

    /**
     * true = 1是、false = 0否
     */
    private Integer auth;

    public ApplicationInterfaceMethodVO() {
    }

    public ApplicationInterfaceMethodVO(String systemId, String interfaceId, String methodId, String methodName, String parameterType, String uri, String httpCommandType, Integer auth) {
        this.systemId = systemId;
        this.interfaceId = interfaceId;
        this.methodId = methodId;
        this.methodName = methodName;
        this.parameterType = parameterType;
        this.uri = uri;
        this.httpCommandType = httpCommandType;
        this.auth = auth;
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

    public String getMethodId() {
        return methodId;
    }

    public void setMethodId(String methodId) {
        this.methodId = methodId;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getParameterType() {
        return parameterType;
    }

    public void setParameterType(String parameterType) {
        this.parameterType = parameterType;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getHttpCommandType() {
        return httpCommandType;
    }

    public void setHttpCommandType(String httpCommandType) {
        this.httpCommandType = httpCommandType;
    }

    public Integer getAuth() {
        return auth;
    }

    public void setAuth(Integer auth) {
        this.auth = auth;
    }
}
