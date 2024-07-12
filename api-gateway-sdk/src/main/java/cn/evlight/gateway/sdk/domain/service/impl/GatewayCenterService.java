package cn.evlight.gateway.sdk.domain.service.impl;

import cn.evlight.gateway.sdk.domain.model.aggregate.RegisterAggregate;
import cn.evlight.gateway.sdk.domain.service.IGatewayCenterService;
import cn.evlight.gateway.sdk.types.enumeration.ResponseCode;
import cn.evlight.gateway.sdk.types.exception.GatewayException;
import cn.evlight.gateway.sdk.types.exception.info.ExceptionInfo;
import cn.evlight.gateway.sdk.types.result.Result;
import cn.hutool.http.ContentType;
import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description: 网关注册中心服务
 * @Author: evlight
 * @Date: 2024/7/9
 */
public class GatewayCenterService implements IGatewayCenterService {

    private final Logger logger = LoggerFactory.getLogger(GatewayCenterService.class);

    @Override
    public void register(String centerAddress, String registerUri, String registerNotifyUri, RegisterAggregate registerAggregate) {
        //系统注册
        String resultStr;
        try {
            resultStr = HttpRequest.post(centerAddress + registerUri)
                    .body(JSON.toJSONString(registerAggregate))
                    .timeout(1000)
                    .header(Header.CONTENT_TYPE, ContentType.JSON.getValue())
                    .header(Header.ACCEPT, ContentType.JSON.getValue())
                    .execute()
                    .body();
        } catch (Exception e) {
            logger.error("[网关注册中心服务]-[注册服务] 注册系统出错 原因:" + e.getMessage());
            throw new GatewayException(ExceptionInfo.SERVICE_REGISTER_ERROR);
        }
        if (StringUtils.isBlank(resultStr)) {
            throw new GatewayException(ExceptionInfo.SERVICE_REGISTER_ERROR);
        }
        Result<Boolean> result = JSON.parseObject(resultStr, new TypeReference<Result<Boolean>>() {
        });
        if (!ResponseCode.SUCCESS.getCode().equals(result.getCode())) {
            throw new GatewayException(ExceptionInfo.SERVICE_REGISTER_FAILED);
        }
        //事件通知
        String eventResultStr;
        try {
            eventResultStr = HttpRequest.post(centerAddress + registerNotifyUri)
                    .form("systemId", registerAggregate.getSystemId())
                    .timeout(1000)
                    .execute()
                    .body();
        } catch (Exception e) {
            logger.error("[网关注册中心服务]-[注册服务] 事件通知出错 原因:" + e.getMessage());
            throw new GatewayException(ExceptionInfo.SERVICE_REGISTER_ERROR);
        }
        if (StringUtils.isBlank(eventResultStr)) {
            throw new GatewayException(ExceptionInfo.SERVICE_REGISTER_ERROR);
        }
        Result<Boolean> eventResult = JSON.parseObject(resultStr, new TypeReference<Result<Boolean>>() {
        });
        if (!ResponseCode.SUCCESS.getCode().equals(eventResult.getCode())) {
            throw new GatewayException(ExceptionInfo.SERVICE_REGISTER_FAILED);
        }
    }

}
