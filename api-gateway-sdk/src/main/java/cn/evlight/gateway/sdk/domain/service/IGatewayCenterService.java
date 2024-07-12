package cn.evlight.gateway.sdk.domain.service;

import cn.evlight.gateway.sdk.domain.model.aggregate.RegisterAggregate;

/**
 * @Description: 网关注册中心服务接口
 * @Author: evlight
 * @Date: 2024/7/9
 */
public interface IGatewayCenterService {

    void register(String centerAddress, String registerUri, String registerNotifyUri, RegisterAggregate registerAggregate);

}
