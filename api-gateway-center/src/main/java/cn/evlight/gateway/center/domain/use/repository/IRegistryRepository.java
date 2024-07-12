package cn.evlight.gateway.center.domain.use.repository;

import cn.evlight.gateway.center.domain.use.model.entity.ApplicationInterfaceEntity;
import cn.evlight.gateway.center.domain.use.model.entity.ApplicationInterfaceMethodEntity;
import cn.evlight.gateway.center.domain.use.model.entity.ApplicationSystemEntity;
import cn.evlight.gateway.center.domain.use.model.entity.GatewayServerDetailEntity;

import java.util.List;

/**
 * @Description: 网关注册仓库接口
 * @Author: evlight
 * @Date: 2024/7/8
 */
public interface IRegistryRepository {

    void registerSystem(ApplicationSystemEntity systemEntity, List<ApplicationInterfaceEntity> interfaceEntities, List<ApplicationInterfaceMethodEntity> methodEntities);

    void registerGateway(GatewayServerDetailEntity gatewayServerDetailEntity);

    List<String> getGatewayDistributionList(String systemId);
}
