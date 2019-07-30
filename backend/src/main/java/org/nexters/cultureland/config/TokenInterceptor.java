package org.nexters.cultureland.config;

import io.jsonwebtoken.Claims;
import org.nexters.cultureland.common.JwtServiceImpl;
import org.nexters.cultureland.common.excepion.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TokenInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    JwtServiceImpl jwtService;
    private final int BEARER_LENGTH = 6;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        if(token == null || token.length() <= BEARER_LENGTH){
            throw new UnauthorizedException("Token is not valid, check your token");
        }
        String tokenType = token.substring(0, BEARER_LENGTH);
        if(!tokenType.equals("Bearer")){
            throw new UnauthorizedException("Token is not valid, check your token type(bearer)");
        }
        String tokenBody = token.substring(BEARER_LENGTH + 1);
        System.out.println("{TOKEN_TYPE:" + tokenType + ", " +
                "TOKEN_BODY:" + tokenBody + "}");
        Claims claims = jwtService.checkJwt(tokenBody);
        request.setAttribute("userId", claims.get("userId"));
        return true;
    }
}
