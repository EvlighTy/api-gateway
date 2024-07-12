package cn.evlight.gateway.center.trigger.http;

import cn.evlight.gateway.center.api.IPullController;
import cn.evlight.gateway.center.api.dto.request.PullRequestDTO;
import cn.evlight.gateway.center.domain.use.model.aggregate.PullAggregate;
import cn.evlight.gateway.center.domain.use.service.IPullService;
import cn.evlight.gateway.center.types.result.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 网关配置拉取api
 * @Author: evlight
 * @Date: 2024/7/8
 */
@RestController
@RequestMapping("/pull")
public class PullController implements IPullController {

    private final Logger logger = LoggerFactory.getLogger(PullController.class);
    @Autowired
    private IPullService pullService;

    @PostMapping("/pull")
    @Override
    public Result<PullAggregate> pull(@RequestBody PullRequestDTO request) {
        logger.info("[服务拉取]-[网关配置拉取] 开始");
        try {
            PullAggregate pullAggregate = pullService.pull(request.getGatewayId(), request.getSystemId());
            logger.info("[服务拉取]-[网关配置拉取] 成功");
            return Result.success(pullAggregate);
        } catch (Exception e) {
            logger.info("[服务拉取]-[网关配置拉取] 失败 原因:" + e.getMessage());
            return Result.error(e.getMessage());
        }
    }
}
