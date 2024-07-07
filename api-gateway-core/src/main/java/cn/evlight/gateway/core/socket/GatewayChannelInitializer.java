package cn.evlight.gateway.core.socket;

import cn.evlight.gateway.core.session.Configuration;
import cn.evlight.gateway.core.session.GatewaySessionFactory;
import cn.evlight.gateway.core.socket.handlers.AuthenticationHandler;
import cn.evlight.gateway.core.socket.handlers.GatewayServerHandler;
import cn.evlight.gateway.core.socket.handlers.ProtocolDataHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description: 网关通信管道初始化器
 * @Author: evlight
 * @Date: 2024/7/4
 */
public class GatewayChannelInitializer extends ChannelInitializer<SocketChannel> {

    private Logger logger = LoggerFactory.getLogger(GatewayChannelInitializer.class);
    private Configuration configuration;
    private GatewaySessionFactory gatewaySessionFactory;

    public GatewayChannelInitializer(Configuration configuration, GatewaySessionFactory gatewaySessionFactory) {
        this.configuration = configuration;
        this.gatewaySessionFactory = gatewaySessionFactory;
    }

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        logger.info("[初始化通信管道]");
        ChannelPipeline pipeline = socketChannel.pipeline();
        pipeline.addLast(new HttpRequestDecoder())
                .addLast(new HttpResponseEncoder())
                .addLast(new HttpObjectAggregator(1024 * 1024))
                .addLast(new GatewayServerHandler(configuration))
                .addLast(new AuthenticationHandler(configuration))
                .addLast(new ProtocolDataHandler(gatewaySessionFactory));
    }
}
