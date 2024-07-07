package cn.evlight.gateway.core.mapping;

import cn.evlight.gateway.core.type.enumeration.HTTPRequestType;

/**
 * @Description: http请求映射信息
 * @Author: evlight
 * @Date: 2024/7/4
 */
public class HTTPStatement {

    private String applicationName; //应用名称
    private String interfaceName; //接口名称
    private String methodName; //方法名称
    private String parameterType; //请求参数类型
    private String uri; //请求路径
    private HTTPRequestType httpRequestType; //请求类型
    private boolean needAuth; //是否需要鉴权

    public HTTPStatement(String applicationName, String interfaceName, String methodName, String parameterType, String uri, HTTPRequestType httpRequestType, boolean needAuth) {
        this.applicationName = applicationName;
        this.interfaceName = interfaceName;
        this.methodName = methodName;
        this.parameterType = parameterType;
        this.uri = uri;
        this.httpRequestType = httpRequestType;
        this.needAuth = needAuth;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
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

    public HTTPRequestType getHttpRequestType() {
        return httpRequestType;
    }

    public void setHttpRequestType(HTTPRequestType httpRequestType) {
        this.httpRequestType = httpRequestType;
    }

    public boolean isNeedAuth() {
        return needAuth;
    }

    public void setNeedAuth(boolean needAuth) {
        this.needAuth = needAuth;
    }
}
