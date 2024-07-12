package cn.evlight.gateway.center.test;

import cn.evlight.gateway.center.api.IPullController;
import cn.evlight.gateway.center.api.dto.request.PullRequestDTO;
import cn.evlight.gateway.center.domain.use.model.aggregate.PullAggregate;
import cn.evlight.gateway.center.types.result.Result;
import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Description: 接口测试
 * @Author: evlight
 * @Date: 2024/7/8
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiTest {

    @Autowired
    private IPullController pullController;

    @Test
    public void test_queryGatewayServerList() {
        PullRequestDTO pullRequestDTO = new PullRequestDTO();
        pullRequestDTO.setGatewayId("api-gateway-g4");
        Result<PullAggregate> result = pullController.pull(pullRequestDTO);
        System.err.println("测试结果:" + JSON.toJSONString(result));
    }

}
