package cn.evlight.gateway.core.socket.handlers;

import cn.evlight.gateway.core.mapping.HTTPStatement;
import cn.evlight.gateway.core.session.Configuration;
import cn.evlight.gateway.core.socket.BaseHandler;
import cn.evlight.gateway.core.socket.agreement.AgreementConstants;
import cn.evlight.gateway.core.socket.agreement.ResponseBuilder;
import cn.evlight.gateway.core.type.enumeration.ResponseCode;
import cn.evlight.gateway.core.type.result.GatewayResult;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import org.apache.dubbo.common.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description: 身份验证处理器
 * @Author: evlight
 * @Date: 2024/7/6
 */
public class AuthenticationHandler extends BaseHandler<FullHttpRequest> {

    private Logger logger = LoggerFactory.getLogger(AuthenticationHandler.class);
    private Configuration configuration;

    public AuthenticationHandler(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    protected void session(ChannelHandlerContext channelHandlerContext, Channel channel, FullHttpRequest request) {
        logger.info("[通道处理器]-[身份验证处理器]");
        try {
            HTTPStatement httpStatement = channel.attr(AgreementConstants.HTTP_STATEMENT).get();
            if (httpStatement.isNeedAuth()) {
                //需要身份验证
                try {
                    String uId = request.headers().get("uId");
                    String token = request.headers().get("token");
                    if (StringUtils.isBlank(token)) {
                        //token为空
                        channel.writeAndFlush(new ResponseBuilder().build(GatewayResult.error(ResponseCode._401)));
                    }
                    boolean authResult = configuration.auth(uId, token);
                    if (!authResult) {
                        //token验证失败
                        channel.writeAndFlush(new ResponseBuilder().build(GatewayResult.error(ResponseCode._401)));
                    }
                } catch (Exception e) {
                    //身份验证出错
                    channel.writeAndFlush(new ResponseBuilder().build(GatewayResult.error(ResponseCode._401)));
                }
            }
            request.retain();
            channelHandlerContext.fireChannelRead(request);
        } catch (Exception e) {
            //会话出错
            channel.writeAndFlush(new ResponseBuilder().build(GatewayResult.error(ResponseCode._500)));
        }
    }
}
