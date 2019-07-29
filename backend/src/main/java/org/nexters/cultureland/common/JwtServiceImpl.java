package org.nexters.cultureland.common;

import io.jsonwebtoken.*;
import org.nexters.cultureland.api.user.model.User;
import org.nexters.cultureland.common.excepion.ForbiddenException;
import org.nexters.cultureland.common.excepion.UnauthorizedException;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtServiceImpl {
    private String secretKey = "cultureland"; // -> 추후 yml, argument로 주입

    public String makeJwt(User user) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        Date expireTime = new Date();
        expireTime.setTime(expireTime.getTime() + 1000 * 60 * 60); // 1시간
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secretKey);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        Map<String, Object> headerMap = new HashMap<String, Object>();

        headerMap.put("typ", "JWT");
        headerMap.put("alg", "HS256");

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", user.getUserId());
        JwtBuilder builder = Jwts.builder().setHeader(headerMap)
                .setClaims(map)
                .setExpiration(expireTime)
                .signWith(signatureAlgorithm, signingKey);
        return builder.compact();
    }

    public boolean checkJwt(String jwt) {
        try {
            Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(secretKey))
                    .parseClaimsJws(jwt).getBody(); // 정상 수행된다면 해당 토큰은 정상토큰
            System.out.println(claims);
            return true;
        } catch (ExpiredJwtException exception) {   //토큰 만료 403
            throw new ForbiddenException("Token is expired, Please refresh your token");
        } catch (JwtException exception) {          //토큰 변조 401
            throw new UnauthorizedException("Please check your jwt token");
        }
    }
}
