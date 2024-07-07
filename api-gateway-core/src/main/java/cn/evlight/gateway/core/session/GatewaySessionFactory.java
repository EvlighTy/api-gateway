package cn.evlight.gateway.core.session;

/**
 * @Description: 网关会话工厂接口
 * @Author: evlight
 * @Date: 2024/7/4
 */
public interface GatewaySessionFactory {

    GatewaySession openSession(String uri);

    Configuration getConfiguration();

}
