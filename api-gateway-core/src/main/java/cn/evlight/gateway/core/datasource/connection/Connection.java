package cn.evlight.gateway.core.datasource.connection;

/**
 * @Description: 连接接口
 * @Author: evlight
 * @Date: 2024/7/4
 */
public interface Connection {

    Object exec(String method, String[] parameterTypes, String[] parameterNames, Object[] args);

}
