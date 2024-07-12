package cn.evlight.gateway.center.domain.loadbalance.service;

import cn.evlight.gateway.center.domain.loadbalance.model.valobj.GatewayServerDetailVO;
import cn.evlight.gateway.center.domain.loadbalance.repository.ILoadBalanceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: 负载均衡抽象类
 * @Author: evlight
 * @Date: 2024/7/11
 */
@Service
public abstract class AbstractLoadBalanceService implements ILoadBalanceService {

    private final Logger logger = LoggerFactory.getLogger(AbstractLoadBalanceService.class);
    @Autowired
    private ILoadBalanceRepository loadBalanceRepository;


    @Override
    public void updateLoadBalance() throws Exception {
        List<GatewayServerDetailVO> gatewayServerDetailVOS = loadBalanceRepository.getGatewayServerDetailList();
        String filePath = createConfig(gatewayServerDetailVOS);
        refreshConfig();
    }

    protected abstract void refreshConfig() throws Exception;

    protected abstract String createConfig(List<GatewayServerDetailVO> gatewayServerDetailVOS) throws Exception;


}
