package cn.evlight.gateway.core.socket.agreement;

import cn.evlight.gateway.core.type.ContentType;
import cn.evlight.gateway.core.type.exception.GatewayException;
import cn.evlight.gateway.core.type.exception.info.ExceptionInfo;
import com.alibaba.fastjson.JSON;
import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.QueryStringDecoder;
import io.netty.handler.codec.http.multipart.Attribute;
import io.netty.handler.codec.http.multipart.HttpPostRequestDecoder;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 请求解析器
 * @Author: evlight
 * @Date: 2024/7/4
 */
public class RequestParser {

    private FullHttpRequest request;

    public RequestParser(FullHttpRequest request) {
        this.request = request;
    }

    //获取请求路径
    public String getUri() {
        String uri = request.uri();
        int index = uri.indexOf("?");
        uri = index > 0 ? uri.substring(0, index) : uri;
        if ("/favicon.ico".equals(uri)) {
            return null;
        }
        return uri;
    }

    //获取请求参数
    public Map<String, Object> getParameters() {
        HttpMethod method = request.method();
        if (HttpMethod.GET.equals(method)) {
            //GET
            HashMap<String, Object> parameters = new HashMap<>();
            QueryStringDecoder queryStringDecoder = new QueryStringDecoder(request.uri());
            queryStringDecoder.parameters().forEach((name, value) -> parameters.put(name, value.get(0)));
            return parameters;
        } else if (HttpMethod.POST.equals(method)) {
            //POST
            String contentType = getContentType();
            switch (contentType){
                case ContentType.APPLICATION_JSON:
                    ByteBuf content = request.content().copy();
                    if(content.isReadable()){
                        return JSON.parseObject(content.toString(StandardCharsets.UTF_8));
                    }
                    return Collections.emptyMap();
                case ContentType.MULTIPART_FORM_DATA:
                    HashMap<String, Object> parameters = new HashMap<>();
                    HttpPostRequestDecoder decoder = new HttpPostRequestDecoder(request);
                    decoder.offer(request);
                    decoder.getBodyHttpDatas().forEach(data->{
                        Attribute attribute = (Attribute) data;
                        try {
                            parameters.put(data.getName(),attribute.getValue());
                        } catch (IOException ignore) {

                        }
                    });
                    return parameters;
                case ContentType.NONE:
                    return Collections.emptyMap();
                default:
                    throw new GatewayException(ExceptionInfo.UNREALIZED_CONTENT_TYPE);
            }
        }
        //TODO 其他请求类型
        throw new GatewayException(ExceptionInfo.UNREALIZED_REQUEST_TYPE);
    }

    //获取请求参数类型
    public String getContentType() {
        Map.Entry<String, String> contentTypeEntry = request.headers()
                .entries()
                .stream()
                .filter(entry -> ContentType.CONTENT_TYPE.equals(entry.getKey()))
                .findAny()
                .orElse(null);
        if (null == contentTypeEntry) {
            return ContentType.NONE;
        }
        String contentType = contentTypeEntry.getValue();
        int index = contentType.indexOf(";");
        return index > 0 ? contentType.substring(0, index) : contentType;
    }

}
