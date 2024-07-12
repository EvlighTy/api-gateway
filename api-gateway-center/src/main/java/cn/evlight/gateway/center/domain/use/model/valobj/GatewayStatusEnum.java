package cn.evlight.gateway.center.domain.use.model.valobj;

/**
 * @Description: 网关状态枚举
 * @Author: evlight
 * @Date: 2024/7/11
 */
public enum GatewayStatusEnum {

    UNAVAILABLE("0", "不可使用"),
    AVAILABLE("1", "可使用");

    private String code;
    private String info;

    GatewayStatusEnum(String code, String info) {
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
