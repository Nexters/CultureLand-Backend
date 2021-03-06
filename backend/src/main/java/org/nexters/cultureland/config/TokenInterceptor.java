package org.nexters.cultureland.config;

import io.jsonwebtoken.Claims;
import org.nexters.cultureland.common.JwtManager;
import org.nexters.cultureland.common.excepion.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TokenInterceptor extends HandlerInterceptorAdapter {
    private static final Logger log = LoggerFactory.getLogger(TokenInterceptor.class);
    @Autowired
    final private JwtManager jwtService;
    final private int BEARER_LENGTH = 6;

    public TokenInterceptor(JwtManager jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (token == null || token.length() <= BEARER_LENGTH) {
            throw new UnauthorizedException("Token is not valid, check your token");
        }
        String tokenType = token.substring(0, BEARER_LENGTH);
        if (!tokenType.equals("Bearer")) {
            throw new UnauthorizedException("Token is not valid, check your token type(bearer)");
        }
        String tokenBody = token.substring(BEARER_LENGTH + 1);
        log.info("{TOKEN_TYPE:" + tokenType + ", " +
                "TOKEN_BODY:" + tokenBody + "}");
        Claims claims = jwtService.checkJwt(tokenBody);
        request.setAttribute("userId", claims.get("userId"));
        return true;
    }
}
