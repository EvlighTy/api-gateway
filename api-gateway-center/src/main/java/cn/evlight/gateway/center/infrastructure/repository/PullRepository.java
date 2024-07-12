package cn.evlight.gateway.center.infrastructure.repository;

import cn.evlight.gateway.center.domain.use.model.valobj.ApplicationInterfaceMethodVO;
import cn.evlight.gateway.center.domain.use.model.valobj.ApplicationInterfaceVO;
import cn.evlight.gateway.center.domain.use.model.valobj.ApplicationSystemVO;
import cn.evlight.gateway.center.domain.use.repository.IPullRepository;
import cn.evlight.gateway.center.infrastructure.dao.ApplicationInterfaceMapper;
import cn.evlight.gateway.center.infrastructure.dao.ApplicationInterfaceMethodMapper;
import cn.evlight.gateway.center.infrastructure.dao.ApplicationSystemMapper;
import cn.evlight.gateway.center.infrastructure.dao.GatewayDistributionMapper;
import cn.evlight.gateway.center.infrastructure.po.ApplicationInterface;
import cn.evlight.gateway.center.infrastructure.po.ApplicationInterfaceMethod;
import cn.evlight.gateway.center.infrastructure.po.ApplicationSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 网关配置拉取仓库
 * @Author: evlight
 * @Date: 2024/7/8
 */
@Repository
public class PullRepository implements IPullRepository {

    @Autowired
    private ApplicationSystemMapper applicationSystemMapper;
    @Autowired
    private GatewayDistributionMapper gatewayDistributionMapper;
    @Autowired
    private ApplicationInterfaceMapper applicationInterfaceMapper;
    @Autowired
    private ApplicationInterfaceMethodMapper applicationInterfaceMethodMapper;

    @Override
    public List<String> getSystemIdByGatewayId(String gatewayId) {
        return gatewayDistributionMapper.getSystemIdsByGatewayId(gatewayId);
    }

    @Override
    public List<ApplicationSystemVO> getApplicationSystemList(List<String> systemIds) {
        List<ApplicationSystem> applicationSystems = applicationSystemMapper.getApplicationSystemList(systemIds);
        ArrayList<ApplicationSystemVO> applicationSystemEntities = new ArrayList<>(applicationSystems.size());
        for (ApplicationSystem applicationSystem : applicationSystems) {
            ApplicationSystemVO applicationSystemVO = new ApplicationSystemVO();
            applicationSystemVO.setId(applicationSystem.getId());
            applicationSystemVO.setSystemId(applicationSystem.getSystemId());
            applicationSystemVO.setSystemName(applicationSystem.getSystemName());
            applicationSystemVO.setSystemType(applicationSystem.getSystemType());
            applicationSystemVO.setSystemRegistry(applicationSystem.getSystemRegistry());
            applicationSystemEntities.add(applicationSystemVO);
        }
        return applicationSystemEntities;
    }

    @Override
    public List<ApplicationInterfaceVO> getApplicationInterfaceListBySystemIds(List<String> systemIds) {
        List<ApplicationInterface> applicationInterfaces = applicationInterfaceMapper.getApplicationInterfaceListBySystemIds(systemIds);
        ArrayList<ApplicationInterfaceVO> applicationInterfaceEntities = new ArrayList<>(applicationInterfaces.size());
        for (ApplicationInterface applicationInterface : applicationInterfaces) {
            ApplicationInterfaceVO applicationInterfaceVO = new ApplicationInterfaceVO();
            applicationInterfaceVO.setId(applicationInterface.getId());
            applicationInterfaceVO.setSystemId(applicationInterface.getSystemId());
            applicationInterfaceVO.setInterfaceId(applicationInterface.getInterfaceId());
            applicationInterfaceVO.setInterfaceName(applicationInterface.getInterfaceName());
            applicationInterfaceVO.setInterfaceVersion(applicationInterface.getInterfaceVersion());
            applicationInterfaceEntities.add(applicationInterfaceVO);
        }
        return applicationInterfaceEntities;
    }

    @Override
    public List<ApplicationInterfaceMethodVO> getApplicationInterfaceMethodList(List<String> systemIds, List<String> interfaceIds) {
        List<ApplicationInterfaceMethod> applicationInterfaceMethods = applicationInterfaceMethodMapper.getApplicationInterfaceMethodList(systemIds, interfaceIds);
        ArrayList<ApplicationInterfaceMethodVO> applicationInterfaceMethodEntities = new ArrayList<>(applicationInterfaceMethods.size());
        for (ApplicationInterfaceMethod applicationInterfaceMethod : applicationInterfaceMethods) {
            ApplicationInterfaceMethodVO applicationInterfaceMethodVO = new ApplicationInterfaceMethodVO();
            applicationInterfaceMethodVO.setId(applicationInterfaceMethod.getId());
            applicationInterfaceMethodVO.setSystemId(applicationInterfaceMethod.getSystemId());
            applicationInterfaceMethodVO.setInterfaceId(applicationInterfaceMethod.getInterfaceId());
            applicationInterfaceMethodVO.setMethodId(applicationInterfaceMethod.getMethodId());
            applicationInterfaceMethodVO.setMethodName(applicationInterfaceMethod.getMethodName());
            applicationInterfaceMethodVO.setParameterType(applicationInterfaceMethod.getParameterType());
            applicationInterfaceMethodVO.setUri(applicationInterfaceMethod.getUri());
            applicationInterfaceMethodVO.setHttpCommandType(applicationInterfaceMethod.getHttpCommandType());
            applicationInterfaceMethodVO.setAuth(applicationInterfaceMethod.getAuth());
            applicationInterfaceMethodEntities.add(applicationInterfaceMethodVO);
        }
        return applicationInterfaceMethodEntities;
    }
}
