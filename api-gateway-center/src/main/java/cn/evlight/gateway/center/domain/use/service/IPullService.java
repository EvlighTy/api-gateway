package cn.evlight.gateway.center.domain.use.service;

import cn.evlight.gateway.center.domain.use.model.aggregate.PullAggregate;

/**
 * @Description: 网关配置拉取服务接口
 * @Author: evlight
 * @Date: 2024/7/8
 */
public interface IPullService {

    PullAggregate pull(String gatewayId, String systemId);

}
