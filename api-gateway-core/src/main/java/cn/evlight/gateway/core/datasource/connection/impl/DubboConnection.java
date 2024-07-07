package cn.evlight.gateway.core.datasource.connection.impl;

import cn.evlight.gateway.core.datasource.connection.Connection;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.bootstrap.DubboBootstrap;
import org.apache.dubbo.config.utils.ReferenceConfigCache;
import org.apache.dubbo.rpc.service.GenericService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description: dubbo数据源
 * @Author: evlight
 * @Date: 2024/7/4
 */
public class DubboConnection implements Connection {

    private final Logger logger = LoggerFactory.getLogger(DubboConnection.class);
    private final GenericService genericService;

    public DubboConnection(ApplicationConfig applicationConfig, RegistryConfig registryConfig, ReferenceConfig<GenericService> referenceConfig) {
        logger.info("[Dubbo服务] 启动中...");
        DubboBootstrap dubboBootstrap = DubboBootstrap.getInstance();
        dubboBootstrap.application(applicationConfig)
                .registry(registryConfig)
                .reference(referenceConfig)
                .start();
        ReferenceConfigCache referenceConfigCache = ReferenceConfigCache.getCache();
        this.genericService = referenceConfigCache.get(referenceConfig);
        logger.info("[Dubbo服务] 获取泛化调用完成");
    }

    @Override
    public Object exec(String method, String[] parameterTypes, String[] parameterNames, Object[] args) {
        return genericService.$invoke(method, parameterTypes, args);
    }
}
