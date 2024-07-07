package cn.evlight.gateway.core.mapper;

import cn.evlight.gateway.core.mapping.HTTPStatement;
import cn.evlight.gateway.core.session.GatewaySession;
import net.sf.cglib.core.Signature;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.InterfaceMaker;
import org.objectweb.asm.Type;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description: 映射代理工厂
 * @Author: evlight
 * @Date: 2024/7/4
 */
public class MapperProxyFactory {

    private final String uri;

    public MapperProxyFactory(String uri) {
        this.uri = uri;
    }

    private final Map<String, IGenericReference> genericReferenceCache = new ConcurrentHashMap<>();

    public IGenericReference newInstance(GatewaySession gatewaySession) {
        return genericReferenceCache.computeIfAbsent(uri, uri -> {
            HTTPStatement httpStatement = gatewaySession.getConfiguration().getHTTPStatement(uri);
            MapperProxy mapperProxy = new MapperProxy(gatewaySession, uri);
            //接口
            InterfaceMaker interfaceMaker = new InterfaceMaker();
            interfaceMaker.add(new Signature(httpStatement.getMethodName(), Type.getType(String.class), new Type[]{Type.getType(String.class)}), null);
            Class<?> clazz = interfaceMaker.create();
            //代理对象
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(Object.class);
            enhancer.setInterfaces(new Class[]{IGenericReference.class, clazz});
            enhancer.setCallback(mapperProxy);
            return (IGenericReference) enhancer.create();
        });
    }
}
