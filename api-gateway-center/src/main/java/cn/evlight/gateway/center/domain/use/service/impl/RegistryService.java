package cn.evlight.gateway.center.domain.use.service.impl;

import cn.evlight.gateway.center.domain.use.model.entity.ApplicationInterfaceEntity;
import cn.evlight.gateway.center.domain.use.model.entity.ApplicationInterfaceMethodEntity;
import cn.evlight.gateway.center.domain.use.model.entity.ApplicationSystemEntity;
import cn.evlight.gateway.center.domain.use.model.entity.GatewayServerDetailEntity;
import cn.evlight.gateway.center.domain.use.repository.IRegistryRepository;
import cn.evlight.gateway.center.domain.use.service.IRegistryService;
import cn.evlight.gateway.center.types.constant.Character;
import cn.evlight.gateway.center.types.enumeration.RedisTopic;
import org.redisson.api.RTopic;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: 网关注册服务
 * @Author: evlight
 * @Date: 2024/7/8
 */
@Service
public class RegistryService implements IRegistryService {

    @Autowired
    private RedissonClient redissonClient;
    @Autowired
    private IRegistryRepository registryRepository;

    @Override
    public void registerSystem(ApplicationSystemEntity systemEntity, List<ApplicationInterfaceEntity> interfaceEntities, List<ApplicationInterfaceMethodEntity> methodEntities) {
        registryRepository.registerSystem(systemEntity, interfaceEntities, methodEntities);
    }

    @Override
    public void registerGateway(GatewayServerDetailEntity gatewayServerDetailEntity) {
        registryRepository.registerGateway(gatewayServerDetailEntity);
    }

    @Override
    public void registerSystemEvent(String systemId) {
        List<String> gatewayIds = registryRepository.getGatewayDistributionList(systemId);
        if (null == gatewayIds || gatewayIds.isEmpty()) {
            return;
        }
        for (String gatewayId : gatewayIds) {
            RTopic topic = redissonClient.getTopic(RedisTopic.REGISTER_SYSTEM_TOPIC.getKey() + Character.COLON + gatewayId);
            topic.publish(systemId);
        }
    }
}
