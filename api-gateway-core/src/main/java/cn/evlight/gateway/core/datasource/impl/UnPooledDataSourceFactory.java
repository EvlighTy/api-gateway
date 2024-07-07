package cn.evlight.gateway.core.datasource.impl;

import cn.evlight.gateway.core.datasource.DataSource;
import cn.evlight.gateway.core.datasource.DataSourceFactory;
import cn.evlight.gateway.core.session.Configuration;
import cn.evlight.gateway.core.type.enumeration.DataSourceType;

/**
 * @Description: 非池化数据源工厂
 * @Author: evlight
 * @Date: 2024/7/4
 */
public class UnPooledDataSourceFactory implements DataSourceFactory {

    @Override
    public DataSource getDataSource(Configuration configuration, String uri) {
        return new UnPooledDataSource(configuration,
                configuration.getHTTPStatement(uri),
                DataSourceType.DUBBO);
    }
}
