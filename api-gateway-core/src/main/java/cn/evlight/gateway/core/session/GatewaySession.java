package cn.evlight.gateway.core.session;

import cn.evlight.gateway.core.mapper.IGenericReference;

import java.util.Map;

/**
 * @Description: 网关会话接口
 * @Author: evlight
 * @Date: 2024/7/4
 */
public interface GatewaySession {

    Object get(String methodName, Map<String, Object> params);

    Object post(String methodName, Map<String, Object> params);

    Object put(String methodName, Map<String, Object> params);

    Object delete(String methodName, Map<String, Object> params);

    IGenericReference getMapper();

    Configuration getConfiguration();

}
