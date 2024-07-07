package cn.evlight.gateway.core.socket.agreement;

import cn.evlight.gateway.core.type.Characters;
import cn.evlight.gateway.core.type.ConstantString;
import cn.evlight.gateway.core.type.ContentType;
import cn.evlight.gateway.core.type.result.GatewayResult;
import com.alibaba.fastjson.JSON;
import io.netty.handler.codec.http.*;

/**
 * @Description: 响应结果构建器
 * @Author: evlight
 * @Date: 2024/7/4
 */
public class ResponseBuilder {

    public DefaultFullHttpResponse build(GatewayResult<?> result){
        DefaultFullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK);
        response.content().writeBytes(JSON.toJSONString(result).getBytes()); //数据
        HttpHeaders headers = response.headers();
        //头信息
        headers.add(HttpHeaderNames.CONTENT_TYPE, ContentType.APPLICATION_JSON_UTF8)
                .add(HttpHeaderNames.CONTENT_LENGTH,response.content().readableBytes())
                .add(HttpHeaderNames.CONNECTION,HttpHeaderValues.KEEP_ALIVE)
                .add(HttpHeaderNames.ACCESS_CONTROL_ALLOW_ORIGIN, Characters.ASTERISK)
                .add(HttpHeaderNames.ACCESS_CONTROL_ALLOW_HEADERS, Characters.ASTERISK)
                .add(HttpHeaderNames.ACCESS_CONTROL_ALLOW_METHODS, ConstantString.AllowMethod.ALL)
                .add(HttpHeaderNames.ACCESS_CONTROL_ALLOW_CREDENTIALS, ConstantString.Boolean.TRUE);
        return response;
    }

}
