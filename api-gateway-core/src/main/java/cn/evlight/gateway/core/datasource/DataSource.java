package cn.evlight.gateway.core.datasource;

import cn.evlight.gateway.core.datasource.connection.Connection;

/**
 * @Description: 数据源接口
 * @Author: evlight
 * @Date: 2024/7/4
 */
public interface DataSource {

    Connection getConnection();

}
