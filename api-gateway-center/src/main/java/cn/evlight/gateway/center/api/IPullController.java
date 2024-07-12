package cn.evlight.gateway.center.api;

import cn.evlight.gateway.center.api.dto.request.PullRequestDTO;
import cn.evlight.gateway.center.domain.use.model.aggregate.PullAggregate;
import cn.evlight.gateway.center.types.result.Result;

/**
 * @Description: 网关配置拉取api接口
 * @Author: evlight
 * @Date: 2024/7/8
 */
public interface IPullController {

    Result<PullAggregate> pull(PullRequestDTO request);

}
