package cn.evlight.gateway.core.socket;

import cn.evlight.gateway.core.session.Configuration;
import cn.evlight.gateway.core.session.GatewaySessionFactory;
import cn.evlight.gateway.core.type.exception.GatewayException;
import cn.evlight.gateway.core.type.exception.info.ExceptionInfo;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.util.concurrent.Callable;

/**
 * @Description: 网关通信服务器
 * @Author: evlight
 * @Date: 2024/7/4
 */
public class GatewaySocketServer implements Callable<Channel> {

    private Logger logger = LoggerFactory.getLogger(GatewaySocketServer.class);
    private Configuration configuration;
    private GatewaySessionFactory gatewaySessionFactory;
    private EventLoopGroup boss;
    private EventLoopGroup work;
    private Channel channel;

    public GatewaySocketServer(Configuration configuration, GatewaySessionFactory gatewaySessionFactory) {
        this.configuration = configuration;
        this.gatewaySessionFactory = gatewaySessionFactory;
        this.boss = new NioEventLoopGroup(configuration.getBossNThread());
        this.work = new NioEventLoopGroup(configuration.getWorkNThread());
    }

    @Override
    public Channel call() throws Exception {
        logger.info("[启动网关] 启动中...");
        ChannelFuture channelFuture = null;
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(boss, work)
                    .channel(NioServerSocketChannel.class) //channel类型
                    .option(ChannelOption.SO_BACKLOG, 128) //可连接队列大小
                    .childHandler(new GatewayChannelInitializer(configuration, gatewaySessionFactory)); //通道处理器
            channelFuture = serverBootstrap.bind(new InetSocketAddress(configuration.getHostName(), configuration.getPort())).syncUninterruptibly();
//            channelFuture = serverBootstrap.bind(configuration.getPort()).syncUninterruptibly();
            channel = channelFuture.channel();
        } catch (Exception e) {
            throw new GatewayException(ExceptionInfo.SOCKET_START_ERROR);
        } finally {
            if(null == channelFuture || !channelFuture.isSuccess()){
                System.err.println(ExceptionInfo.SOCKET_START_ERROR);
            }
        }
        logger.info("[启动网关] 完成");
        return channel;
    }
}
