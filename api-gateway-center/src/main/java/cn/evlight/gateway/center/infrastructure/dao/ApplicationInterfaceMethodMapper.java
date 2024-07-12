package cn.evlight.gateway.center.infrastructure.dao;

import cn.evlight.gateway.center.infrastructure.po.ApplicationInterfaceMethod;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author evlight
 * @since 2024-07-08
 */
@Mapper
public interface ApplicationInterfaceMethodMapper {

    List<ApplicationInterfaceMethod> getApplicationInterfaceMethodList(List<String> systemIds, List<String> interfaceIds);

    void insertBatches(ArrayList<ApplicationInterfaceMethod> applicationInterfaceMethods);

    List<String> isExistBatches(ArrayList<ApplicationInterfaceMethod> applicationInterfaceMethods);
}
