package cn.evlight.gateway.core.mapper;

import cn.evlight.gateway.core.mapping.HTTPStatement;
import cn.evlight.gateway.core.session.Configuration;
import cn.evlight.gateway.core.session.GatewaySession;
import cn.evlight.gateway.core.type.enumeration.HTTPRequestType;
import cn.evlight.gateway.core.type.exception.GatewayException;
import cn.evlight.gateway.core.type.exception.info.ExceptionInfo;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * @Description: http方法映射
 * @Author: evlight
 * @Date: 2024/7/4
 */
public class HTTPMethodMapper {

    private String methodName;
    private HTTPRequestType httpRequestType;

    public HTTPMethodMapper(String uri, Configuration configuration, Method method) {
        HTTPStatement httpStatement = configuration.getHTTPStatement(uri);
        this.methodName = httpStatement.getMethodName();
        this.httpRequestType = httpStatement.getHttpRequestType();
    }

    public Object exec(GatewaySession gatewaySession, Map<String,Object> params){
        Object result;
        switch (httpRequestType){
            case GET:
                result = gatewaySession.get(methodName, params);
                break;
            case POST:
                result = gatewaySession.post(methodName, params);
                break;
            case PUT:
                result = gatewaySession.put(methodName, params);
                break;
            case DELETE:
                result = gatewaySession.delete(methodName, params);
                break;
            default:
                throw new GatewayException(ExceptionInfo.UNKNOWN_HTTP_REQUEST_TYPE);
        }
        return result;
    }
}
