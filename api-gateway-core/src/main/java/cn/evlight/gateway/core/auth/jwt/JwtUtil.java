package cn.evlight.gateway.core.auth.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Collections;
import java.util.Date;
import java.util.Map;

/**
 * @Description: jwt解析工具类
 * @Author: evlight
 * @Date: 2024/7/6
 */
public class JwtUtil {

    private static final String signKey = "evlight";

    //生成jwt令牌
    public static String encode(String issuer, long ttlMillis, Map<String, Object> claims) {
        if (null == claims) {
            claims = Collections.emptyMap();
        }
        long nowMillis = System.currentTimeMillis();
        Date issueData = new Date(nowMillis);
        JwtBuilder jwtBuilder = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(issueData)
                .setIssuer(issuer)
                .signWith(SignatureAlgorithm.HS256, signKey);
        if (ttlMillis > 0) {
            Date expiredDate = new Date(nowMillis + ttlMillis);
            jwtBuilder.setExpiration(expiredDate);
        }
        return jwtBuilder.compact();
    }

    //解析jwt令牌
    public static Claims decode(String token) {
        return Jwts.parser()
                .setSigningKey(signKey)
                .parseClaimsJwt(token)
                .getBody();
    }

}
