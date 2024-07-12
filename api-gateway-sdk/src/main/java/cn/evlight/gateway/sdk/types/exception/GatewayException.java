package cn.evlight.gateway.sdk.types.exception;

/**
 * @Description: 网关异常
 * @Author: evlight
 * @Date: 2024/7/4
 */
public class GatewayException extends RuntimeException {
    private static final long serialVersionUID = 5317680961212299217L;

    public GatewayException(String msg) {
        super(msg);
    }
}
