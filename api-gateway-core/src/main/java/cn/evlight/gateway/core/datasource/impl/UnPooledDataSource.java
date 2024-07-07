package cn.evlight.gateway.core.datasource.impl;

import cn.evlight.gateway.core.datasource.DataSource;
import cn.evlight.gateway.core.datasource.connection.Connection;
import cn.evlight.gateway.core.datasource.connection.impl.DubboConnection;
import cn.evlight.gateway.core.mapping.HTTPStatement;
import cn.evlight.gateway.core.session.Configuration;
import cn.evlight.gateway.core.type.enumeration.DataSourceType;
import cn.evlight.gateway.core.type.exception.GatewayException;
import cn.evlight.gateway.core.type.exception.info.ExceptionInfo;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.rpc.service.GenericService;

/**
 * @Description: 非池化数据源
 * @Author: evlight
 * @Date: 2024/7/4
 */
public class UnPooledDataSource implements DataSource {

    private Configuration configuration;
    private HTTPStatement httpStatement;
    private DataSourceType dataSourceType;

    public UnPooledDataSource(Configuration configuration, HTTPStatement httpStatement, DataSourceType dataSourceType) {
        this.configuration = configuration;
        this.httpStatement = httpStatement;
        this.dataSourceType = dataSourceType;
    }

    @Override
    public Connection getConnection() {
        switch (dataSourceType) {
            case HTTP:
                //TODO 暂时不实现
                break;
            case DUBBO:
                String applicationName = httpStatement.getApplicationName();
                String interfaceName = httpStatement.getInterfaceName();
                ApplicationConfig applicationConfig = configuration.getApplicationConfig(applicationName);
                RegistryConfig registryConfig = configuration.getRegistryConfig(applicationName);
                ReferenceConfig<GenericService> referenceConfig = configuration.getReferenceConfig(interfaceName);
                return new DubboConnection(applicationConfig, registryConfig, referenceConfig);
            default:
                throw new GatewayException(ExceptionInfo.UNKNOWN_DATA_SOURCE_TYPE);
        }
        throw new GatewayException(ExceptionInfo.UNKNOWN_DATA_SOURCE_TYPE);
    }
}
