package cn.evlight.gateway.center.infrastructure.dao;

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
public interface GatewayDistributionMapper {

    List<String> getSystemIdsByGatewayId(String gatewayId);

    List<String> getGatewayDistributionList(String systemId);
}
