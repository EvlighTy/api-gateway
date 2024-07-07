package cn.evlight.gateway.core.executor;

import cn.evlight.gateway.core.mapping.HTTPStatement;
import cn.evlight.gateway.core.type.result.SessionResult;

import java.util.Map;

/**
 * @Description: 执行器接口
 * @Author: evlight
 * @Date: 2024/7/4
 */
public interface Executor {

    SessionResult<?> exec(HTTPStatement httpStatement, Map<String, Object> params);

}
