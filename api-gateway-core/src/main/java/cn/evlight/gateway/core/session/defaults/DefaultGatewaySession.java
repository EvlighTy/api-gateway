package cn.evlight.gateway.core.session.defaults;

import cn.evlight.gateway.core.executor.Executor;
import cn.evlight.gateway.core.mapper.IGenericReference;
import cn.evlight.gateway.core.mapping.HTTPStatement;
import cn.evlight.gateway.core.session.Configuration;
import cn.evlight.gateway.core.session.GatewaySession;
import cn.evlight.gateway.core.type.exception.GatewayException;
import cn.evlight.gateway.core.type.exception.info.ExceptionInfo;

import java.util.Map;

/**
 * @Description: 默认网关会话
 * @Author: evlight
 * @Date: 2024/7/4
 */
public class DefaultGatewaySession implements GatewaySession {

    private Configuration configuration;
    private String uri;
    private Executor executor;

    public DefaultGatewaySession(Configuration configuration, String uri, Executor executor) {
        this.configuration = configuration;
        this.uri = uri;
        this.executor = executor;
    }

    @Override
    public Object get(String methodName, Map<String, Object> params) {
        HTTPStatement httpStatement = configuration.getHTTPStatement(uri);
        try {
            return executor.exec(httpStatement, params);
        } catch (Exception e) {
            throw new GatewayException(ExceptionInfo.EXECUTOR_ERROR);
        }
    }

    @Override
    public Object post(String methodName, Map<String, Object> params) {
        return get(methodName, params);
    }

    @Override
    public Object put(String methodName, Map<String, Object> params) {
        return null;
    }

    @Override
    public Object delete(String methodName, Map<String, Object> params) {
        return null;
    }

    @Override
    public IGenericReference getMapper() {
        return configuration.getMapper(uri, this);
    }

    @Override
    public Configuration getConfiguration() {
        return configuration;
    }
}
