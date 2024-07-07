package cn.evlight.gateway.core.executor.impl;

import cn.evlight.gateway.core.datasource.connection.Connection;
import cn.evlight.gateway.core.executor.BaseExecutor;
import cn.evlight.gateway.core.session.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description: 默认执行器
 * @Author: evlight
 * @Date: 2024/7/4
 */
public class DefaultExecutor extends BaseExecutor {

    private final Logger logger = LoggerFactory.getLogger(DefaultExecutor.class);

    public DefaultExecutor(Configuration configuration, Connection connection) {
        super(configuration, connection);
        logger.info("[默认执行器] 创建执行器完成");
    }

    @Override
    protected Object doExec(String methodName, String[] parameterTypes, Object[] args) {
        return connection.exec(methodName, parameterTypes, new String[]{"ignore"}, args);
    }
}
