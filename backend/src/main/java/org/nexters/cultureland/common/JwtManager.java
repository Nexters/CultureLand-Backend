package org.nexters.cultureland.common;

import io.jsonwebtoken.*;
import org.nexters.cultureland.api.model.User;
import org.nexters.cultureland.common.excepion.ForbiddenException;
import org.nexters.cultureland.common.excepion.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtManager {
    private static final Logger log = LoggerFactory.getLogger(JwtManager.class);
    @Value("${jwtSecretKey}")
    private String secretKey; //argument로 주입

    public String makeJwt(User user) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        Date expireTime = new Date();
        expireTime.setTime(expireTime.getTime() + 1000 * 60 * 60); // 현재 5분 -> 1시간
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secretKey);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        Map<String, Object> headerMap = new HashMap<String, Object>();

        headerMap.put("typ", "JWT");
        headerMap.put("alg", "HS256");

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userId", user.getUserId());
        JwtBuilder builder = Jwts.builder().setHeader(headerMap)
                .setClaims(map)
                .setExpiration(expireTime)
                .setIssuedAt(new Date())
                .signWith(signatureAlgorithm, signingKey);
        return builder.compact();
    }

    public Claims checkJwt(String jwt) {
        try {
            Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(secretKey))
                    .parseClaimsJws(jwt).getBody(); // 정상 수행된다면 해당 토큰은 정상토큰
            log.info(claims.toString());
            return claims;
        } catch (ExpiredJwtException exception) {   //토큰 만료 403
            throw new ForbiddenException("Token is expired, Please refresh your token");
        } catch (JwtException exception) {          //토큰 변조 401
            throw new UnauthorizedException("Please check your jwt token");
        }
    }
}
