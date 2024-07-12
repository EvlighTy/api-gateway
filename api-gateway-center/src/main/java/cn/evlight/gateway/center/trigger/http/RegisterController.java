package cn.evlight.gateway.center.trigger.http;

import cn.evlight.gateway.center.api.IRegisterController;
import cn.evlight.gateway.center.api.dto.request.RegisterGatewayRequestDTO;
import cn.evlight.gateway.center.api.dto.request.RegisterSystemRequestDTO;
import cn.evlight.gateway.center.domain.loadbalance.service.ILoadBalanceService;
import cn.evlight.gateway.center.domain.use.model.entity.ApplicationInterfaceEntity;
import cn.evlight.gateway.center.domain.use.model.entity.ApplicationInterfaceMethodEntity;
import cn.evlight.gateway.center.domain.use.model.entity.ApplicationSystemEntity;
import cn.evlight.gateway.center.domain.use.model.entity.GatewayServerDetailEntity;
import cn.evlight.gateway.center.domain.use.model.valobj.ApplicationInterfaceVO;
import cn.evlight.gateway.center.domain.use.model.valobj.GatewayStatusEnum;
import cn.evlight.gateway.center.domain.use.service.IRegistryService;
import cn.evlight.gateway.center.types.result.Result;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description: 服务注册api
 * @Author: evlight
 * @Date: 2024/7/10
 */
@RestController
@RequestMapping("/register")
public class RegisterController implements IRegisterController {

    private final Logger logger = LoggerFactory.getLogger(RegisterController.class);
    @Autowired
    private IRegistryService registryService;
    @Autowired
    private ILoadBalanceService loadBalanceService;

    @PostMapping("/system")
    @Override
    public Result<Boolean> registerSystem(@RequestBody RegisterSystemRequestDTO request) {
        logger.info("[服务注册]-[注册服务] 开始 params:{}", JSON.toJSONString(request));
        try {
            //系统
            ApplicationSystemEntity applicationSystemEntity = new ApplicationSystemEntity();
            applicationSystemEntity.setSystemId(request.getSystemId());
            applicationSystemEntity.setSystemName(request.getSystemName());
            applicationSystemEntity.setSystemType(request.getSystemType());
            applicationSystemEntity.setSystemRegistry(request.getSystemRegistry());
            //接口
            List<ApplicationInterfaceVO> applicationInterfaceVOS = request.getApplicationInterfaceVOS();
            List<ApplicationInterfaceEntity> interfaceEntities = applicationInterfaceVOS.stream()
                    .map(interfaceVO -> {
                        ApplicationInterfaceEntity interfaceEntity = new ApplicationInterfaceEntity();
                        interfaceEntity.setSystemId(interfaceVO.getSystemId());
                        interfaceEntity.setInterfaceId(interfaceVO.getInterfaceId());
                        interfaceEntity.setInterfaceName(interfaceVO.getInterfaceName());
                        interfaceEntity.setInterfaceVersion(interfaceVO.getInterfaceVersion());
                        return interfaceEntity;
                    }).collect(Collectors.toList());
            //方法
            List<ApplicationInterfaceMethodEntity> methodEntities = applicationInterfaceVOS.stream()
                    .flatMap(applicationInterfaceVO -> applicationInterfaceVO.getApplicationInterfaceMethodVOS().stream())
                    .map(methodVO -> {
                        ApplicationInterfaceMethodEntity methodEntity = new ApplicationInterfaceMethodEntity();
                        methodEntity.setSystemId(methodVO.getSystemId());
                        methodEntity.setInterfaceId(methodVO.getInterfaceId());
                        methodEntity.setMethodId(methodVO.getMethodId());
                        methodEntity.setMethodName(methodVO.getMethodName());
                        methodEntity.setParameterType(methodVO.getParameterType());
                        methodEntity.setUri(methodVO.getUri());
                        methodEntity.setHttpCommandType(methodVO.getHttpCommandType());
                        methodEntity.setAuth(methodVO.getAuth());
                        return methodEntity;
                    }).collect(Collectors.toList());
            //注册系统
            registryService.registerSystem(applicationSystemEntity, interfaceEntities, methodEntities);
            //更新负载均衡配置
            loadBalanceService.updateLoadBalance();
            return Result.success(Boolean.TRUE);
        } catch (Exception e) {
            return Result.error(Boolean.FALSE);
        }
    }


    @PostMapping("/gateway")
    @Override
    public Result<Boolean> registerGateway(@RequestBody RegisterGatewayRequestDTO request) {
        logger.info("[服务注册]-[注册网关] 开始 params:{}", JSON.toJSONString(request));
        try {
            GatewayServerDetailEntity gatewayServerDetailEntity = new GatewayServerDetailEntity();
            gatewayServerDetailEntity.setGroupId(request.getGroupId());
            gatewayServerDetailEntity.setGatewayId(request.getGatewayId());
            gatewayServerDetailEntity.setGatewayName(request.getGatewayName());
            gatewayServerDetailEntity.setGatewayAddress(request.getGatewayAddress());
            gatewayServerDetailEntity.setStatus(GatewayStatusEnum.AVAILABLE.getCode());
            registryService.registerGateway(gatewayServerDetailEntity);
            return Result.success(Boolean.TRUE);
        } catch (Exception e) {
            return Result.error(Boolean.FALSE);
        }
    }

    @PostMapping("/registerSystemEvent")
    @Override
    public Result<Boolean> registerSystemEvent(@RequestParam String systemId) {
        logger.info("[服务注册]-[注册服务事件通知] 开始 params:{}", systemId);
        try {
            registryService.registerSystemEvent(systemId);
            return Result.error(Boolean.TRUE);
        } catch (Exception e) {
            return Result.error(Boolean.FALSE);
        }
    }
}
