package cn.evlight.gateway.core.auth.impl;

import cn.evlight.gateway.core.auth.IAuth;
import cn.evlight.gateway.core.auth.jwt.JwtAuthenticationToken;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;

/**
 * @Description: jwt身份验证服务
 * @Author: evlight
 * @Date: 2024/7/6
 */
public class JwtAuthService implements IAuth {

    private Subject subject;

    public JwtAuthService() {
        IniSecurityManagerFactory iniSecurityManagerFactory = new IniSecurityManagerFactory("classpath:shiro.ini");
        SecurityManager securityManager = iniSecurityManagerFactory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        subject = SecurityUtils.getSubject();
    }

    @Override
    public boolean identify(String id, String token) {
        try{
            subject.login(new JwtAuthenticationToken(id,token));
            return subject.isAuthenticated();
        }finally {
            subject.logout();
        }
    }
}
