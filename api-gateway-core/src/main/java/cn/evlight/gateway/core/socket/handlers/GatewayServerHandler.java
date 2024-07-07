package cn.evlight.gateway.core.socket.handlers;

import cn.evlight.gateway.core.mapping.HTTPStatement;
import cn.evlight.gateway.core.session.Configuration;
import cn.evlight.gateway.core.socket.BaseHandler;
import cn.evlight.gateway.core.socket.agreement.AgreementConstants;
import cn.evlight.gateway.core.socket.agreement.RequestParser;
import cn.evlight.gateway.core.socket.agreement.ResponseBuilder;
import cn.evlight.gateway.core.type.enumeration.ResponseCode;
import cn.evlight.gateway.core.type.result.GatewayResult;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description: 网关入口处理器
 * @Author: evlight
 * @Date: 2024/7/6
 */
public class GatewayServerHandler extends BaseHandler<FullHttpRequest> {

    private Logger logger = LoggerFactory.getLogger(GatewayServerHandler.class);
    private final Configuration configuration;

    public GatewayServerHandler(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    protected void session(ChannelHandlerContext channelHandlerContext, Channel channel, FullHttpRequest request) {
        logger.info("[通道处理器]-[网关入口处理器]");
        try {
            RequestParser requestParser = new RequestParser(request);
            String uri = requestParser.getUri();
            HTTPStatement httpStatement = configuration.getHTTPStatement(uri);
            channel.attr(AgreementConstants.HTTP_STATEMENT).set(httpStatement);
            request.retain();
            channelHandlerContext.fireChannelRead(request);
        }catch (Exception e){
            channel.writeAndFlush(new ResponseBuilder().build(GatewayResult.error(ResponseCode._500)));
        }
    }
}
