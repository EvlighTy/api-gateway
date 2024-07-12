package cn.evlight.gateway.assist.types.result;

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
}
