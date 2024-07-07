import cn.evlight.gateway.core.mapping.HTTPStatement;
import cn.evlight.gateway.core.session.Configuration;
import cn.evlight.gateway.core.session.defaults.DefaultGatewaySessionFactory;
import cn.evlight.gateway.core.socket.GatewaySocketServer;
import cn.evlight.gateway.core.type.enumeration.HTTPRequestType;
import cn.evlight.gateway.core.type.exception.GatewayException;
import io.netty.channel.Channel;
import org.junit.Test;

import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @Description: 网关测试
 * @Author: evlight
 * @Date: 2024/7/6
 */
public class GatewayTest {

    @Test
    public void gateway_test() throws Exception {
        Configuration configuration = new Configuration("127.0.0.1", 8081);
        DefaultGatewaySessionFactory defaultGatewaySessionFactory = new DefaultGatewaySessionFactory(configuration);
        GatewaySocketServer server = new GatewaySocketServer(configuration, defaultGatewaySessionFactory);
        Future<Channel> future = Executors.newFixedThreadPool(2).submit(server);
        Channel channel = future.get();
        if (null == channel) {
            throw new GatewayException("netty server start error, channel is null");
        }
        while (!channel.isActive()) {
            System.out.println("netty server starting...");
            Thread.sleep(1000);
        }
        System.out.println("netty server start success");
        configuration.registryConfig("api-gateway-test-provider", "zookeeper://127.0.0.1:2181", "cn.bugstack.gateway.rpc.IActivityBooth", "1.0.0");
        HTTPStatement httpStatement = new HTTPStatement(
                "api-gateway-test-provider",
                "cn.bugstack.gateway.rpc.IActivityBooth",
                "sayHi",
                "java.lang.String",
                "/wg/activity/sayHi",
                HTTPRequestType.GET,
                false);
        configuration.addMapper(httpStatement);
        Thread.sleep(Long.MAX_VALUE);
    }
}
