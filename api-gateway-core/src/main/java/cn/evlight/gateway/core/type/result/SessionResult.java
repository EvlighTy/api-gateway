package cn.evlight.gateway.core.type.result;

import cn.evlight.gateway.core.type.enumeration.ResultCode;

/**
 * @Description: 会话响应结果
 * @Author: evlight
 * @Date: 2024/7/4
 */
public class SessionResult<T> {
    private String code;
    private String info;
    private T data;

    public SessionResult(String code, String info, T data) {
        this.code = code;
        this.info = info;
        this.data = data;
    }

    public static <T> SessionResult<T> success(){
        return new SessionResult<>(ResultCode.SUCCESS.getCode(),
                ResultCode.SUCCESS.getInfo(),
                null);
    }

    public static <T> SessionResult<T> success(T data){
        return new SessionResult<>(ResultCode.SUCCESS.getCode(),
                ResultCode.SUCCESS.getInfo(),
                data);
    }

    public static <T> SessionResult<T> error(){
        return new SessionResult<>(ResultCode.ERROR.getCode(),
                ResultCode.ERROR.getInfo(),
                null);
    }

    public static <T> SessionResult<T> error(T data){
        return new SessionResult<>(ResultCode.ERROR.getCode(),
                ResultCode.ERROR.getInfo(),
                data);
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
}
