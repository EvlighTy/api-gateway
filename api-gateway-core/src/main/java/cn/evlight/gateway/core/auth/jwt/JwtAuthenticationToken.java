package cn.evlight.gateway.core.auth.jwt;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @Description: jwt身份验证token
 * @Author: evlight
 * @Date: 2024/7/6
 */
public class JwtAuthenticationToken implements AuthenticationToken {

    private String channelId; //通信管道ID
    private String jwtToken; //jwt令牌

    public JwtAuthenticationToken(String channelId, String jwtToken) {
        this.channelId = channelId;
        this.jwtToken = jwtToken;
    }

    @Override
    public Object getPrincipal() {
        return channelId;
    }

    @Override
    public Object getCredentials() {
        return jwtToken;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }
}
