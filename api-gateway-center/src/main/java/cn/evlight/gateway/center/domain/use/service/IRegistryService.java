package cn.evlight.gateway.center.domain.use.service;

import cn.evlight.gateway.center.domain.use.model.entity.ApplicationInterfaceEntity;
import cn.evlight.gateway.center.domain.use.model.entity.ApplicationInterfaceMethodEntity;
import cn.evlight.gateway.center.domain.use.model.entity.ApplicationSystemEntity;
import cn.evlight.gateway.center.domain.use.model.entity.GatewayServerDetailEntity;

import java.util.List;

/**
 * @Description: 网关注册服务接口
 * @Author: evlight
 * @Date: 2024/7/8
 */
public interface IRegistryService {

    void registerSystem(ApplicationSystemEntity systemEntity, List<ApplicationInterfaceEntity> interfaceEntities, List<ApplicationInterfaceMethodEntity> methodEntities);

    void registerGateway(GatewayServerDetailEntity gatewayServerDetailEntity);

    void registerSystemEvent(String systemId);
}
