package cn.evlight.gateway.core.socket.handlers;

import cn.evlight.gateway.core.mapper.IGenericReference;
import cn.evlight.gateway.core.session.GatewaySession;
import cn.evlight.gateway.core.session.GatewaySessionFactory;
import cn.evlight.gateway.core.socket.BaseHandler;
import cn.evlight.gateway.core.socket.agreement.RequestParser;
import cn.evlight.gateway.core.socket.agreement.ResponseBuilder;
import cn.evlight.gateway.core.type.Characters;
import cn.evlight.gateway.core.type.enumeration.ResponseCode;
import cn.evlight.gateway.core.type.enumeration.ResultCode;
import cn.evlight.gateway.core.type.result.GatewayResult;
import cn.evlight.gateway.core.type.result.SessionResult;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * @Description: 协议数据处理器
 * @Author: evlight
 * @Date: 2024/7/4
 */
public class ProtocolDataHandler extends BaseHandler<FullHttpRequest> {

    private Logger logger = LoggerFactory.getLogger(ProtocolDataHandler.class);
    private final GatewaySessionFactory gatewaySessionFactory;

    public ProtocolDataHandler(GatewaySessionFactory gatewaySessionFactory) {
        this.gatewaySessionFactory = gatewaySessionFactory;
    }

    @Override
    protected void session(ChannelHandlerContext channelHandlerContext, Channel channel, FullHttpRequest request) {
        logger.info("[通道处理器]-[协议数据处理器]");
        try {
            RequestParser requestParser = new RequestParser(request);
            String uri = requestParser.getUri();
            if (uri == null) {
                return;
            }
            Map<String, Object> parameters = requestParser.getParameters();
            GatewaySession gatewaySession = gatewaySessionFactory.openSession(uri);
            IGenericReference reference = gatewaySession.getMapper();
            SessionResult<?> result = reference.$invoke(parameters);
            channel.writeAndFlush(new ResponseBuilder().build(ResultCode.SUCCESS.getCode().equals(result.getCode()) ?
                    GatewayResult.success(result.getData(), getNodeInfo()) :
                    GatewayResult.error(ResponseCode._404, getNodeInfo())));
        } catch (Exception e) {
            channel.writeAndFlush(new ResponseBuilder().build(GatewayResult.error(ResponseCode._502, getNodeInfo())));
        }
    }

    private String getNodeInfo() {
        return gatewaySessionFactory.getConfiguration().getHostName() +
                Characters.COLON +
                gatewaySessionFactory.getConfiguration().getPort();
    }

}
