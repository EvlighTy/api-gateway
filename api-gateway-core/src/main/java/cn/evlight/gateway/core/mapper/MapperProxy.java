package cn.evlight.gateway.core.mapper;

import cn.evlight.gateway.core.session.GatewaySession;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * @Description: 映射代理
 * @Author: evlight
 * @Date: 2024/7/4
 */
public class MapperProxy implements MethodInterceptor {

    private GatewaySession gatewaySession;
    private final String uri;

    public MapperProxy(GatewaySession gatewaySession, String uri) {
        this.gatewaySession = gatewaySession;
        this.uri = uri;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        HTTPMethodMapper mapper = new HTTPMethodMapper(uri, gatewaySession.getConfiguration(), method);
        //TODO 暂时只处理第一个参数
        return mapper.exec(gatewaySession, (Map<String, Object>) args[0]);
    }
}
