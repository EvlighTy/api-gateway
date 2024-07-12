package cn.evlight.gateway.center.types.result;

import cn.evlight.gateway.center.types.enumeration.ResponseCode;

/**
 * @Description: 统一返回对象
 * @Author: evlight
 * @Date: 2024/5/29
 */
public class Result<T> {

    private String code;
    private String info;
    private T data;

    public Result() {
    }

    public Result(String code, String info, T data) {
        this.code = code;
        this.info = info;
        this.data = data;
    }

    public static <T> Result<T> success(T data){
        return new Result<>(ResponseCode.SUCCESS.getCode(),
                ResponseCode.SUCCESS.getInfo(),
                data);
    }

    public static <T> Result<T> success(){
        return new Result<>(ResponseCode.SUCCESS.getCode(),
                ResponseCode.SUCCESS.getInfo(),
                null);
    }

    public static <T> Result<T> error(T data){
        return new Result<>(ResponseCode.SUCCESS.getCode(),
                ResponseCode.SUCCESS.getInfo(),
                data);
    }

    public static <T> Result<T> error(){
        return new Result<>(ResponseCode.SUCCESS.getCode(),
                ResponseCode.SUCCESS.getInfo(),
                null);
    }

    public static <T> Result<T> error(String info){
        return new Result<>(ResponseCode.SUCCESS.getCode(),
                info,
                null);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code='" + code + '\'' +
                ", info='" + info + '\'' +
                ", data=" + data +
                '}';
    }
}
