package cn.evlight.gateway.assist.domain.service.impl;

import cn.evlight.gateway.assist.domain.model.aggregate.PullAggregate;
import cn.evlight.gateway.assist.domain.model.vo.GatewayServerDetailVO;
import cn.evlight.gateway.assist.domain.service.IGatewayCenterService;
import cn.evlight.gateway.assist.types.enumeration.ResponseCode;
import cn.evlight.gateway.assist.types.result.Result;
import cn.evlight.gateway.core.type.exception.GatewayException;
import cn.evlight.gateway.core.type.exception.info.ExceptionInfo;
import cn.hutool.http.ContentType;
import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

/**
 * @Description: 网关中心服务
 * @Author: evlight
 * @Date: 2024/7/8
 */
public class GatewayCenterService implements IGatewayCenterService {

    private final Logger logger = LoggerFactory.getLogger(GatewayCenterService.class);

    @Override
    public void registerGateway(String address, String registerUri, String groupId, String gatewayId, String gatewayName, String gatewayAddress) {
        logger.info("[网关中心服务]-[网关注册] 开始");
        try {
            GatewayServerDetailVO params = new GatewayServerDetailVO(groupId, gatewayId, gatewayName, gatewayAddress);
            String resultStr = HttpRequest.post(address + registerUri)
                    .body(JSON.toJSONString(params))
                    .timeout(1000)
                    .header(Header.CONTENT_TYPE, ContentType.JSON.getValue())
                    .header(Header.ACCEPT, ContentType.JSON.getValue())
                    .execute()
                    .body();
            if (null == resultStr) {
                throw new GatewayException(ExceptionInfo.REGISTER_ERROR);
            }
            Result<Boolean> result = JSON.parseObject(resultStr, new TypeReference<Result<Boolean>>() {
            });
            if (ResponseCode.UN_ERROR.getCode().equals(result.getCode())) {
                throw new GatewayException(ExceptionInfo.REGISTER_FAILED);
            }
            logger.info("[网关中心服务]-[网关注册] 成功");
        } catch (Exception e) {
            logger.info("[网关中心服务]-[网关注册] 失败 原因:{}", e.getMessage());
            throw new GatewayException(ExceptionInfo.REGISTER_ERROR);
        }
    }

    @Override
    public PullAggregate pull(String address, String pullUri, String gatewayId, String systemId) {
        logger.info("[网关中心服务]-[服务拉取] 开始");
        HashMap<String, Object> params = new HashMap<>();
        params.put("gatewayId", gatewayId);
        params.put("systemId", systemId);
        String resultStr = HttpRequest.post(address + pullUri)
                .body(JSON.toJSONString(params))
                .timeout(1000)
                .header(Header.CONTENT_TYPE, ContentType.JSON.getValue())
                .header(Header.ACCEPT, ContentType.JSON.getValue())
                .execute()
                .body();
        try {
            if (null == resultStr) {
                throw new GatewayException(ExceptionInfo.PULL_ERROR);
            }
            Result<PullAggregate> result = JSON.parseObject(resultStr, new TypeReference<Result<PullAggregate>>() {
            });
            if (ResponseCode.UN_ERROR.getCode().equals(result.getCode())) {
                throw new GatewayException(ExceptionInfo.PULL_FAILED);
            }
            logger.info("[网关中心服务]-[服务拉取] 成功");
            return result.getData();
        } catch (Exception e) {
            throw new GatewayException(ExceptionInfo.PULL_ERROR);
        }
    }
}
