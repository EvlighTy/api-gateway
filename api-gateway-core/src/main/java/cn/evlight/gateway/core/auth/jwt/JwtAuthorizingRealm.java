package cn.evlight.gateway.core.auth.jwt;

import cn.evlight.gateway.core.type.exception.GatewayException;
import cn.evlight.gateway.core.type.exception.info.ExceptionInfo;
import io.jsonwebtoken.Claims;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * @Description: jwt身份认证领域
 * @Author: evlight
 * @Date: 2024/7/6
 */
public class JwtAuthorizingRealm extends AuthorizingRealm {

    @Override
    public Class<?> getAuthenticationTokenClass() {
        return JwtAuthenticationToken.class;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //TODO 授权处理
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        try {
            String jwtToken = ((JwtAuthenticationToken) authenticationToken).getJwtToken();
            Claims claims = JwtUtil.decode(jwtToken);
            if (authenticationToken.getPrincipal().equals(claims.getSubject())) {
                throw new GatewayException(ExceptionInfo.INVALID_TOKEN);
            }
        } catch (Exception e) {
            throw new GatewayException(ExceptionInfo.INVALID_TOKEN);
        }
        return new SimpleAuthenticationInfo(authenticationToken.getPrincipal(), authenticationToken.getCredentials(), getName());
    }
}
