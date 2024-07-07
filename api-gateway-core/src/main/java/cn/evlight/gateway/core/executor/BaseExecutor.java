package cn.evlight.gateway.core.executor;

import cn.evlight.gateway.core.datasource.connection.Connection;
import cn.evlight.gateway.core.mapping.HTTPStatement;
import cn.evlight.gateway.core.session.Configuration;
import cn.evlight.gateway.core.type.SimpleTypes;
import cn.evlight.gateway.core.type.result.SessionResult;

import java.util.Map;

/**
 * @Description: 基本执行器
 * @Author: evlight
 * @Date: 2024/7/4
 */
public abstract class BaseExecutor implements Executor {

    protected Configuration configuration;
    protected Connection connection;

    public BaseExecutor(Configuration configuration, Connection connection) {
        this.configuration = configuration;
        this.connection = connection;
    }

    @Override
    public SessionResult<?> exec(HTTPStatement httpStatement, Map<String, Object> params) {
        String methodName = httpStatement.getMethodName();
        String parameterType = httpStatement.getParameterType();
        String[] parameterTypes = new String[]{parameterType};
        Object[] args = SimpleTypes.isSimpleType(parameterType) ? params.values().toArray() : new Object[]{params};
        try {
            Object data = doExec(methodName, parameterTypes, args);
            return SessionResult.success(data);
        } catch (Exception e) {
            return SessionResult.error();
        }
    }

    protected abstract Object doExec(String methodName, String[] parameterTypes, Object[] args);

}
