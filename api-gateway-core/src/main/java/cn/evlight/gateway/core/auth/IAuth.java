package cn.evlight.gateway.core.auth;

/**
 * @Description: 身份验证接口
 * @Author: evlight
 * @Date: 2024/7/6
 */
public interface IAuth {

    boolean identify(String id, String token);

}
