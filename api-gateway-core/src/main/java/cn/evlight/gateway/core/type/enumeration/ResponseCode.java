package cn.evlight.gateway.core.type.enumeration;

/**
 * @Description: 响应码
 * @Author: evlight
 * @Date: 2024/7/4
 */
public enum ResponseCode {

    _200("200", "访问成功"),
    _400("400", "参数类型不匹配"),
    _401("401", "身份验证失败"),
    _403("403", "拒绝请求"),
    _404("404", "请求路径有误"),
    _500("500", "无法完成请求"),
    _502("502", "上游服务器响应错误");

    private String code;
    private String info;

    ResponseCode(String code, String info) {
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
