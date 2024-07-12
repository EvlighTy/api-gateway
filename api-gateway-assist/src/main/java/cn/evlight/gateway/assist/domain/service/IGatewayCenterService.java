package cn.evlight.gateway.assist.domain.service;

import cn.evlight.gateway.assist.domain.model.aggregate.PullAggregate;

/**
 * @Description: 网关中心服务接口
 * @Author: evlight
 * @Date: 2024/7/8
 */
public interface IGatewayCenterService {

    /**
    * @Description: 注册网关
    * @Param: [address, registerUri, groupId, gatewayId, gatewayName, gatewayAddress]
    * @return:
    * @Date: 2024/7/8
    */
    void registerGateway(String address, String registerUri, String groupId, String gatewayId, String gatewayName, String gatewayAddress);

    /**
    * @Description: 拉取服务信息
    * @Param: [address, pullUri, gatewayId, systemId]
    * @return:
    * @Date: 2024/7/8
    */
    PullAggregate pull(String address, String pullUri, String gatewayId, String systemId);

}
