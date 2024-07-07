package cn.evlight.gateway.core.datasource;

import cn.evlight.gateway.core.session.Configuration;

/**
 * @Description: 数据源工厂接口
 * @Author: evlight
 * @Date: 2024/7/4
 */
public interface DataSourceFactory {

    DataSource getDataSource(Configuration configuration, String uri);

}
