package cn.evlight.gateway.center.domain.loadbalance.repository;

import cn.evlight.gateway.center.domain.loadbalance.model.valobj.GatewayServerDetailVO;

import java.util.List;

/**
 * @Description: 负载均衡仓库接口
 * @Author: evlight
 * @Date: 2024/7/11
 */
public interface ILoadBalanceRepository {

    List<GatewayServerDetailVO> getGatewayServerDetailList();

}
