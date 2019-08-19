package org.nexters.cultureland.api.service.impl;

import org.nexters.cultureland.api.model.User;
import org.nexters.cultureland.api.response.KakaoUserResponse;
import org.nexters.cultureland.api.service.SSOService;
import org.nexters.cultureland.api.service.UserService;
import org.nexters.cultureland.common.JwtManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;

@Service
public class KakaoSSOServiceImpl implements SSOService {
    private static final Logger log = LoggerFactory.getLogger(KakaoSSOServiceImpl.class);
    private String baseUrl = "https://kapi.kakao.com";
    private String userUrl = "/v2/user/me";
    private RestTemplate restTemplate;
    private UserService userService;
    private JwtManager jwtManager;

    public KakaoSSOServiceImpl(RestTemplate restTemplate, UserService userService, JwtManager jwtManager) {
        this.jwtManager = jwtManager;
        this.restTemplate = restTemplate;
        this.userService = userService;
    }

    @Transactional
    @Override
    public String signInOrSignUp(String accessToken) {
        long userId = requestUserid(accessToken);
        User user = null;
        synchronized (this) {
            user = userService.createUser(userId);
            return jwtManager.makeJwt(user);
        }
    }

    public KakaoUserResponse getUserInfoFromKakao(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);
        log.info("Request user Information from Kakao - " + baseUrl + userUrl);
        HttpEntity<KakaoUserResponse> kakaoResponse = restTemplate.exchange(baseUrl + userUrl, HttpMethod.POST, new HttpEntity<>(headers), KakaoUserResponse.class);
        log.info("Response user information from Facebook - " + kakaoResponse);

        return kakaoResponse.getBody();
    }

    @Override
    public long requestUserid(String accessToken) {
        KakaoUserResponse kakaoUserResponse = getUserInfoFromKakao(accessToken);
        return kakaoUserResponse.getId();
    }
}
