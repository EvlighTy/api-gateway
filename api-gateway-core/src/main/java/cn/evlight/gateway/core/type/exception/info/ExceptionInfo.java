package cn.evlight.gateway.core.type.exception.info;

/**
 * @Description: 错误信息
 * @Author: evlight
 * @Date: 2024/7/4
 */
public interface ExceptionInfo {
    String UNKNOWN_URI = "请求路径无法识别";
    String GENERIC_ERROR = "泛化调用出错";
    String EXECUTOR_ERROR = "执行器出错";
    String UNKNOWN_DATA_SOURCE_TYPE = "未知数据源类型";
    String UNREALIZED_CONTENT_TYPE = "未实现的内容类型";
    String UNREALIZED_REQUEST_TYPE = "未实现的请求类型";
    String UNKNOWN_HTTP_REQUEST_TYPE = "未知的http请求类型";
    String INVALID_TOKEN = "无效的令牌";
    String SOCKET_START_ERROR = "socket启动出错";
    String REGISTER_FAILED = "网关注册失败";
    String REGISTER_ERROR = "网关注册出错";
    String PULL_FAILED = "网关拉取服务失败";
    String PULL_ERROR = "网关拉取服务出错";
    String INIT_FAILED = "网关初始化失败";
    String CLOSE_FAILED = "网关关闭失败";
    String START_FAILED = "网关启动失败";
}
