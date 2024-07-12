package cn.evlight.gateway.center.infrastructure.repository;

import cn.evlight.gateway.center.domain.loadbalance.model.valobj.GatewayServerDetailVO;
import cn.evlight.gateway.center.domain.loadbalance.repository.ILoadBalanceRepository;
import cn.evlight.gateway.center.infrastructure.dao.GatewayServerDetailMapper;
import cn.evlight.gateway.center.infrastructure.po.GatewayServerDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 负载均衡仓库
 * @Author: evlight
 * @Date: 2024/7/11
 */
@Repository
public class LoadBalanceRepository implements ILoadBalanceRepository {

    @Autowired
    private GatewayServerDetailMapper gatewayServerDetailMapper;

    @Override
    public List<GatewayServerDetailVO> getGatewayServerDetailList() {
        List<GatewayServerDetail> gatewayServerDetails = gatewayServerDetailMapper.getGatewayServerDetailList();
        ArrayList<GatewayServerDetailVO> gatewayServerDetailVOS = new ArrayList<>(gatewayServerDetails.size());
        for (GatewayServerDetail gatewayServerDetail : gatewayServerDetails) {
            GatewayServerDetailVO gatewayServerDetailVO = new GatewayServerDetailVO();
            gatewayServerDetailVO.setId(gatewayServerDetail.getId());
            gatewayServerDetailVO.setGroupId(gatewayServerDetail.getGroupId());
            gatewayServerDetailVO.setGatewayId(gatewayServerDetail.getGatewayId());
            gatewayServerDetailVO.setGatewayName(gatewayServerDetail.getGatewayName());
            gatewayServerDetailVO.setGatewayAddress(gatewayServerDetail.getGatewayAddress());
            gatewayServerDetailVO.setStatus(gatewayServerDetail.getStatus());
            gatewayServerDetailVOS.add(gatewayServerDetailVO);
        }
        return gatewayServerDetailVOS;
    }
}
