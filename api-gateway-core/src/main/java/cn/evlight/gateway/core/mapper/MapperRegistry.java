package cn.evlight.gateway.core.mapper;

import cn.evlight.gateway.core.mapping.HTTPStatement;
import cn.evlight.gateway.core.session.Configuration;
import cn.evlight.gateway.core.session.GatewaySession;
import cn.evlight.gateway.core.type.exception.GatewayException;
import cn.evlight.gateway.core.type.exception.info.ExceptionInfo;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 映射器注册
 * @Author: evlight
 * @Date: 2024/7/4
 */
public class MapperRegistry {

    private Configuration configuration;

    public MapperRegistry(Configuration configuration) {
        this.configuration = configuration;
    }

    private final Map<String, MapperProxyFactory> mappers = new HashMap<>();

    public IGenericReference getMapper(String uri, GatewaySession gatewaySession) {
        MapperProxyFactory mapperProxyFactory = mappers.get(uri);
        if (null == mapperProxyFactory) {
            throw new GatewayException(ExceptionInfo.UNKNOWN_URI);
        }
        try {
            return mapperProxyFactory.newInstance(gatewaySession);
        } catch (Exception e) {
            throw new GatewayException(ExceptionInfo.GENERIC_ERROR);
        }
    }

    public void addMapper(HTTPStatement httpStatement) {
        String uri = httpStatement.getUri();
        if (mappers.containsKey(uri)) {
            return;
        }
        mappers.put(uri, new MapperProxyFactory(uri));
        configuration.addHTTPStatement(httpStatement);
    }
}
