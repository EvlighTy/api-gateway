package cn.evlight.gateway.assist.types.enumeration;

/**
 * @Description:
 * @Author: evlight
 * @Date: 2024/7/12
 */
public enum RedisTopic {

    REGISTER_SYSTEM_TOPIC("register_system_topic","注册系统");

    private String key;
    private String info;

    RedisTopic(String key, String info) {
        this.key = key;
        this.info = info;
    }

    public String getKey() {
        return key;
    }

    public String getInfo() {
        return info;
    }
}
