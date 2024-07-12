package cn.evlight.gateway.center.infrastructure.dao;

import cn.evlight.gateway.center.infrastructure.po.ApplicationSystem;
import org.apache.ibatis.annotations.Mapper;

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
public interface ApplicationSystemMapper {

    List<ApplicationSystem> getApplicationSystemList(List<String> systemIds);

    void insert(ApplicationSystem applicationSystem);

    int isExist(String systemId);
}
