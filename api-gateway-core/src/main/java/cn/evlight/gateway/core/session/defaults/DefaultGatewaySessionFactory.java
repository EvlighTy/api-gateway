package cn.evlight.gateway.core.session.defaults;

import cn.evlight.gateway.core.datasource.DataSource;
import cn.evlight.gateway.core.datasource.impl.UnPooledDataSourceFactory;
import cn.evlight.gateway.core.executor.Executor;
import cn.evlight.gateway.core.session.Configuration;
import cn.evlight.gateway.core.session.GatewaySession;
import cn.evlight.gateway.core.session.GatewaySessionFactory;

/**
 * @Description: 默认网关会话工厂
 * @Author: evlight
 * @Date: 2024/7/4
 */
public class DefaultGatewaySessionFactory implements GatewaySessionFactory {

    private final Configuration configuration;

    public DefaultGatewaySessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public GatewaySession openSession(String uri) {
        UnPooledDataSourceFactory unPooledDataSourceFactory = new UnPooledDataSourceFactory();
        DataSource dataSource = unPooledDataSourceFactory.getDataSource(configuration, uri);
        Executor executor = configuration.newExecutor(dataSource.getConnection());
        return new DefaultGatewaySession(configuration, uri, executor);
    }

    @Override
    public Configuration getConfiguration(){
        return configuration;
    }

}
