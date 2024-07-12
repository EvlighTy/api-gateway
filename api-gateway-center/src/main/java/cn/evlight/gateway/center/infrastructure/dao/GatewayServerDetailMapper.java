package cn.evlight.gateway.center.infrastructure.dao;

import cn.evlight.gateway.center.infrastructure.po.GatewayServerDetail;
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
public interface GatewayServerDetailMapper {

    List<GatewayServerDetail> getGatewayServerDetailList();

    int isExist(GatewayServerDetail gatewayServerDetail);

    void insert(GatewayServerDetailMapper gatewayServerDetailMapper);
}
