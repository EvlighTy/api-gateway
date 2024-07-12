package cn.evlight.gateway.assist.trigger.listener;

import cn.evlight.gateway.assist.application.GatewayApplication;
import org.redisson.api.listener.MessageListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description: 系统注册监听器
 * @Author: evlight
 * @Date: 2024/7/9
 */
public class RegisterServiceListener implements MessageListener<String> {

    private Logger logger = LoggerFactory.getLogger(RegisterServiceListener.class);
    private GatewayApplication gatewayApplication;

    public RegisterServiceListener(GatewayApplication gatewayApplication) {
        this.gatewayApplication = gatewayApplication;
    }

    @Override
    public void onMessage(CharSequence charSequence, String systemId) {
        logger.info("[listener]-[系统注册消息监听] 监听到消息");
        gatewayApplication.pull(systemId);
    }
}
