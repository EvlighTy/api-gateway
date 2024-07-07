package cn.evlight.gateway.core.type.result;

import cn.evlight.gateway.core.type.enumeration.ResponseCode;

/**
 * @Description: 网关服务响应结果
 * @Author: evlight
 * @Date: 2024/7/4
 */
public class GatewayResult<T> {

    private String code;
    private String info;
    private T data;
    private String gatewayNode;

    public GatewayResult(String code, String info, T data, String gatewayNode) {
        this.code = code;
        this.info = info;
        this.data = data;
        this.gatewayNode = gatewayNode;
    }

    public static <T> GatewayResult<T> success() {
        return new GatewayResult<>(ResponseCode._200.getCode(),
                ResponseCode._200.getInfo(),
                null,
                null);
    }

    public static <T> GatewayResult<T> success(String gatewayNode) {
        return new GatewayResult<>(ResponseCode._200.getCode(),
                ResponseCode._200.getInfo(),
                null,
                gatewayNode);
    }

    public static <T> GatewayResult<T> success(T data, String gatewayNode) {
        return new GatewayResult<>(ResponseCode._200.getCode(),
                ResponseCode._200.getInfo(),
                data,
                gatewayNode);
    }

    public static <T> GatewayResult<T> error(ResponseCode responseCode) {
        return new GatewayResult<>(responseCode.getCode(),
                responseCode.getInfo(),
                null,
                null);
    }

    public static <T> GatewayResult<T> error(ResponseCode responseCode,String gatewayNode) {
        return new GatewayResult<>(responseCode.getCode(),
                responseCode.getInfo(),
                null,
                gatewayNode);
    }

    public static <T> GatewayResult<T> error(ResponseCode responseCode,T data, String gatewayNode) {
        return new GatewayResult<>(responseCode.getCode(),
                responseCode.getInfo(),
                data,
                gatewayNode);
    }

    public String getCode() {
        return code;
    }

    public String getInfo() {
        return info;
    }

    public T getData() {
        return data;
    }

    public String getGatewayNode() {
        return gatewayNode;
    }
}
