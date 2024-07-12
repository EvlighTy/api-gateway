package cn.evlight.gateway.center.domain.use.repository;

import cn.evlight.gateway.center.domain.use.model.valobj.ApplicationInterfaceMethodVO;
import cn.evlight.gateway.center.domain.use.model.valobj.ApplicationInterfaceVO;
import cn.evlight.gateway.center.domain.use.model.valobj.ApplicationSystemVO;

import java.util.List;

/**
 * @Description: 网关配置拉取仓库接口
 * @Author: evlight
 * @Date: 2024/7/8
 */
public interface IPullRepository {

    List<String> getSystemIdByGatewayId(String gatewayId);

    List<ApplicationSystemVO> getApplicationSystemList(List<String> systemIds);

    List<ApplicationInterfaceVO> getApplicationInterfaceListBySystemIds(List<String> systemIds);

    List<ApplicationInterfaceMethodVO> getApplicationInterfaceMethodList(List<String> systemIds, List<String> interfaceIds);
}
