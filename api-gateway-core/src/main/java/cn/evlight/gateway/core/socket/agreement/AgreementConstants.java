package cn.evlight.gateway.core.socket.agreement;

import cn.evlight.gateway.core.mapping.HTTPStatement;
import io.netty.util.AttributeKey;

/**
 * @Description: 协议常量
 * @Author: evlight
 * @Date: 2024/7/4
 */
public class AgreementConstants {

    public static final AttributeKey<HTTPStatement> HTTP_STATEMENT = AttributeKey.valueOf("httpStatement");

}
