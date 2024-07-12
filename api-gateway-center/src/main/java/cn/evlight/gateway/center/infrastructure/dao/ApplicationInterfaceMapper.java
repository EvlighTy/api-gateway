package cn.evlight.gateway.center.infrastructure.dao;

import cn.evlight.gateway.center.infrastructure.po.ApplicationInterface;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author evlight
 * @since 2024-07-08
 */
@Mapper
public interface ApplicationInterfaceMapper {

    List<ApplicationInterface> getApplicationInterfaceListBySystemIds(List<String> systemIds);

    void insertBatches(ArrayList<ApplicationInterface> applicationInterfaces);

    List<String> isExistBatches(List<ApplicationInterface> applicationInterfaces);
}
