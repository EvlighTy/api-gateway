package cn.evlight.gateway.core.socket;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Description: 基本处理器
 * @Author: evlight
 * @Date: 2024/7/4
 */
public abstract class BaseHandler<T> extends SimpleChannelInboundHandler<T> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, T msg) throws Exception {
        session(channelHandlerContext, channelHandlerContext.channel(), msg);
    }

    protected abstract void session(ChannelHandlerContext channelHandlerContext, final Channel channel, T msg);

}
