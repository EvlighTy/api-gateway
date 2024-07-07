package cn.evlight.gateway.core.mapper;

import cn.evlight.gateway.core.type.result.SessionResult;

import java.util.Map;

/**
 * @Description: 泛化调用接口
 * @Author: evlight
 * @Date: 2024/7/4
 */
public interface IGenericReference {

    SessionResult $invoke(Map<String, Object> params);

}
