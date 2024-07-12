package cn.evlight.gateway.center.api;

import cn.evlight.gateway.center.api.dto.request.RegisterGatewayRequestDTO;
import cn.evlight.gateway.center.api.dto.request.RegisterSystemRequestDTO;
import cn.evlight.gateway.center.types.result.Result;

/**
 * @Description: 服务注册api接口
 * @Author: evlight
 * @Date: 2024/7/10
 */
public interface IRegisterController {

    Result<Boolean> registerSystem(RegisterSystemRequestDTO request);

    Result<Boolean> registerGateway(RegisterGatewayRequestDTO request);

    Result<Boolean> registerSystemEvent(String systemId);

}
