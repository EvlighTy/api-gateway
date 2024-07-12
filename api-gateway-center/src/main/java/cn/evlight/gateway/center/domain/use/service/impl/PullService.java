package cn.evlight.gateway.center.domain.use.service.impl;

import cn.evlight.gateway.center.domain.use.model.aggregate.PullAggregate;
import cn.evlight.gateway.center.domain.use.model.valobj.ApplicationInterfaceMethodVO;
import cn.evlight.gateway.center.domain.use.model.valobj.ApplicationInterfaceVO;
import cn.evlight.gateway.center.domain.use.model.valobj.ApplicationSystemVO;
import cn.evlight.gateway.center.domain.use.repository.IPullRepository;
import cn.evlight.gateway.center.domain.use.service.IPullService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Description: 网关配置拉取服务
 * @Author: evlight
 * @Date: 2024/7/8
 */
@Service
public class PullService implements IPullService {

    @Autowired
    private IPullRepository pullRepository;

    @Override
    public PullAggregate pull(String gatewayId, String systemId) {
        List<String> systemIds = new ArrayList<>();
        if (StringUtils.isBlank(systemId)) {
            systemIds = pullRepository.getSystemIdByGatewayId(gatewayId);
            if (systemIds.isEmpty()) {
                return PullAggregate.empty(gatewayId);
            }
        } else {
            systemIds.add(systemId);
        }
        List<ApplicationSystemVO> applicationSystemEntities = pullRepository.getApplicationSystemList(systemIds);
        List<ApplicationInterfaceVO> applicationInterfaceEntities = pullRepository.getApplicationInterfaceListBySystemIds(systemIds);
        Map<String, List<ApplicationInterfaceVO>> applicationInterfaceMap = applicationInterfaceEntities.stream()
                .collect(Collectors.groupingBy(ApplicationInterfaceVO::getSystemId));
        List<String> interfaceIds = applicationInterfaceEntities.stream()
                .map(ApplicationInterfaceVO::getInterfaceId)
                .collect(Collectors.toList());
        List<ApplicationInterfaceMethodVO> applicationInterfaceMethodEntities = pullRepository.getApplicationInterfaceMethodList(systemIds, interfaceIds);
        Map<String, List<ApplicationInterfaceMethodVO>> applicationInterfaceMethodMap = applicationInterfaceMethodEntities.stream()
                .collect(Collectors.groupingBy(ApplicationInterfaceMethodVO::getInterfaceId));
        for (ApplicationSystemVO applicationSystemVO : applicationSystemEntities) {
            List<ApplicationInterfaceVO> interfaceGroup = applicationInterfaceMap.get(applicationSystemVO.getSystemId());
            if (null != interfaceGroup && !interfaceGroup.isEmpty()) {
                applicationSystemVO.setApplicationInterfaceVOS(interfaceGroup);
                for (ApplicationInterfaceVO applicationInterfaceVO : interfaceGroup) {
                    List<ApplicationInterfaceMethodVO> methodGroup = applicationInterfaceMethodMap.get(applicationInterfaceVO.getInterfaceId());
                    if (null != methodGroup && !methodGroup.isEmpty()) {
                        applicationInterfaceVO.setApplicationInterfaceMethodVOS(methodGroup);
                    }
                }
            }
        }
        return new PullAggregate(gatewayId, applicationSystemEntities);
    }
}
