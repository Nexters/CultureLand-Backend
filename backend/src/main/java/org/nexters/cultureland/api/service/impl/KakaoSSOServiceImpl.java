package org.nexters.cultureland.api.service.impl;

import org.nexters.cultureland.api.model.User;
import org.nexters.cultureland.api.repo.UserRepository;
import org.nexters.cultureland.api.response.KakaoUserResponse;
import org.nexters.cultureland.api.service.SSOService;
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
    private UserRepository userRepository;
    private JwtManager jwtManager;

    public KakaoSSOServiceImpl(RestTemplate restTemplate, UserRepository userRepository, JwtManager jwtManager) {
        this.jwtManager = jwtManager;
        this.restTemplate = restTemplate;
        this.userRepository = userRepository;
    }

    @Transactional
    @Override
    public String signInOrSignUp(String accessToken) {
        long userId = requestUserid(accessToken);

        synchronized (this) {
            boolean userExists = userRepository.existsByuserId(userId);
            User user = null;
            if (!userExists) {
                user = User.builder()
                        .userId(userId)
                        .build();
                userRepository.save(user);
            } else {
                userRepository.findByuserId(userId);
            }
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
