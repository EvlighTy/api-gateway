package cn.evlight.gateway.center.infrastructure.repository;

import cn.evlight.gateway.center.domain.use.model.entity.ApplicationInterfaceEntity;
import cn.evlight.gateway.center.domain.use.model.entity.ApplicationInterfaceMethodEntity;
import cn.evlight.gateway.center.domain.use.model.entity.ApplicationSystemEntity;
import cn.evlight.gateway.center.domain.use.model.entity.GatewayServerDetailEntity;
import cn.evlight.gateway.center.domain.use.repository.IRegistryRepository;
import cn.evlight.gateway.center.infrastructure.dao.*;
import cn.evlight.gateway.center.infrastructure.po.ApplicationInterface;
import cn.evlight.gateway.center.infrastructure.po.ApplicationInterfaceMethod;
import cn.evlight.gateway.center.infrastructure.po.ApplicationSystem;
import cn.evlight.gateway.center.infrastructure.po.GatewayServerDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description: 网关注册仓库
 * @Author: evlight
 * @Date: 2024/7/8
 */
@Repository
public class RegistryRepository implements IRegistryRepository {

    private final Logger logger = LoggerFactory.getLogger(RegistryRepository.class);
    @Autowired
    private ApplicationSystemMapper applicationSystemMapper;
    @Autowired
    private ApplicationInterfaceMapper applicationInterfaceMapper;
    @Autowired
    private ApplicationInterfaceMethodMapper applicationInterfaceMethodMapper;
    @Autowired
    private GatewayServerDetailMapper gatewayServerDetailMapper;
    @Autowired
    private GatewayDistributionMapper gatewayDistributionMapper;

    @Transactional
    @Override
    public void registerSystem(ApplicationSystemEntity systemEntity, List<ApplicationInterfaceEntity> interfaceEntities, List<ApplicationInterfaceMethodEntity> methodEntities) {
        //系统
        ApplicationSystem applicationSystem = new ApplicationSystem();
        applicationSystem.setSystemId(systemEntity.getSystemId());
        applicationSystem.setSystemName(systemEntity.getSystemName());
        applicationSystem.setSystemType(systemEntity.getSystemType());
        applicationSystem.setSystemRegistry(systemEntity.getSystemRegistry());
        int exist = applicationSystemMapper.isExist(systemEntity.getSystemId());
        if (exist == 1) {
            logger.info("[服务注册]-[系统注册] 系统重复注册 systemId:{}", systemEntity.getSystemId());
        } else {
            applicationSystemMapper.insert(applicationSystem);
        }
        //接口
        ArrayList<ApplicationInterface> applicationInterfaces = new ArrayList<>(interfaceEntities.size());
        for (ApplicationInterfaceEntity interfaceEntity : interfaceEntities) {
            ApplicationInterface applicationInterface = new ApplicationInterface();
            applicationInterface.setSystemId(interfaceEntity.getSystemId());
            applicationInterface.setInterfaceId(interfaceEntity.getInterfaceId());
            applicationInterface.setInterfaceName(interfaceEntity.getInterfaceName());
            applicationInterface.setInterfaceVersion(interfaceEntity.getInterfaceVersion());
            applicationInterfaces.add(applicationInterface);
        }
        List<String> interfaceIds = applicationInterfaceMapper.isExistBatches(applicationInterfaces);
        if (null != interfaceIds && !interfaceIds.isEmpty()) {
            logger.info("[服务注册]-[系统注册] 接口重复注册 interfaceIds:{}", interfaceIds);
            if (interfaceIds.size() != interfaceEntities.size()) {
                applicationInterfaces = applicationInterfaces.stream()
                        .filter(interface_ -> !interfaceIds.contains(interface_.getInterfaceId()))
                        .collect(Collectors.toCollection(ArrayList::new));
                applicationInterfaceMapper.insertBatches(applicationInterfaces);
            }
        } else {
            applicationInterfaceMapper.insertBatches(applicationInterfaces);
        }
        //方法
        ArrayList<ApplicationInterfaceMethod> applicationInterfaceMethods = new ArrayList<>(methodEntities.size());
        for (ApplicationInterfaceMethodEntity methodEntity : methodEntities) {
            ApplicationInterfaceMethod applicationInterfaceMethod = new ApplicationInterfaceMethod();
            applicationInterfaceMethod.setSystemId(methodEntity.getSystemId());
            applicationInterfaceMethod.setInterfaceId(methodEntity.getInterfaceId());
            applicationInterfaceMethod.setMethodId(methodEntity.getMethodId());
            applicationInterfaceMethod.setMethodName(methodEntity.getMethodName());
            applicationInterfaceMethod.setParameterType(methodEntity.getParameterType());
            applicationInterfaceMethod.setUri(methodEntity.getUri());
            applicationInterfaceMethod.setHttpCommandType(methodEntity.getHttpCommandType());
            applicationInterfaceMethod.setAuth(methodEntity.getAuth());
            applicationInterfaceMethods.add(applicationInterfaceMethod);
        }
        List<String> methodIds = applicationInterfaceMethodMapper.isExistBatches(applicationInterfaceMethods);
        if (null != methodIds && !methodIds.isEmpty()) {
            logger.info("[服务注册]-[系统注册] 方法重复注册 methodIds:{}", methodIds);
            if (methodIds.size() != methodEntities.size()) {
                applicationInterfaceMethods = applicationInterfaceMethods.stream()
                        .filter(method -> !methodIds.contains(method.getMethodId()))
                        .collect(Collectors.toCollection(ArrayList::new));
                applicationInterfaceMethodMapper.insertBatches(applicationInterfaceMethods);
            }
        } else {
            applicationInterfaceMethodMapper.insertBatches(applicationInterfaceMethods);
        }
    }

    @Override
    public void registerGateway(GatewayServerDetailEntity gatewayServerDetailEntity) {
        GatewayServerDetail gatewayServerDetail = new GatewayServerDetail();
        gatewayServerDetail.setGroupId(gatewayServerDetailEntity.getGroupId());
        gatewayServerDetail.setGatewayId(gatewayServerDetailEntity.getGatewayId());
        gatewayServerDetail.setGatewayName(gatewayServerDetailEntity.getGatewayName());
        gatewayServerDetail.setGatewayAddress(gatewayServerDetailEntity.getGatewayAddress());
        gatewayServerDetail.setStatus(gatewayServerDetailEntity.getStatus());
        int exist = gatewayServerDetailMapper.isExist(gatewayServerDetail);
        if (exist == 1) {
            logger.info("[服务注册]-[网关注册] 网关重复注册 gatewayId:{}", gatewayServerDetail.getGatewayId());
        } else {
            gatewayServerDetailMapper.insert(gatewayServerDetailMapper);
        }
    }

    @Override
    public List<String> getGatewayDistributionList(String systemId) {
        return gatewayDistributionMapper.getGatewayDistributionList(systemId);
    }

}
