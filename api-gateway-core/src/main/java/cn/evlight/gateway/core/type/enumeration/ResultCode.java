package cn.evlight.gateway.core.type.enumeration;

/**
 * @Description: 响应结果code
 * @Author: evlight
 * @Date: 2024/7/4
 */
public enum ResultCode {

    SUCCESS("0000","调用成功"),
    ERROR("0001","调用失败")
    ;
    private String code;
    private String info;

    ResultCode() {
    }

    ResultCode(String code, String info) {
        this.code = code;
        this.info = info;
    }

    public String getCode() {
        return code;
    }

    public String getInfo() {
        return info;
    }
}
