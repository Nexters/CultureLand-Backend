package org.nexters.cultureland.service.impl;

import org.nexters.cultureland.common.KakaoUserResponse;
import org.nexters.cultureland.model.User;
import org.nexters.cultureland.repo.UserRepository;
import org.nexters.cultureland.service.SSOService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;

@Service
public class KakaoSSOServiceImpl implements SSOService {
    private String baseUrl = "https://kapi.kakao.com";
    private String tokenUrl = "/v1/user/access_token_info";
    private String userUrl = "/v2/user/me";
    private final boolean SUCCESS = true;
    private final boolean FAILED = false;
    private final long NotFoundKakaoId = -1L;
    private RestTemplate restTemplate;
    private UserRepository userRepository;

    @Transactional
    @Override
    public boolean signInOrSignUp(String accessToken){
//                이 부분이 필요한가에 대한 의문
//        KakaoTokenResponse kakaoResponse = requestKakaoToken(accessToken);
//        Long requestUserId = getIdFromRespsonse(kakaoResponse);
//        if(requestUserId == NotFoundKakaoId) {throw new BadRequestException("Not Matched Your id");}

        long userId = requestUserid(accessToken);

        synchronized (this){
            boolean userExists = userRepository.existsByuserId(userId);
            if(!userExists) {
                User user = User.builder()
                        .userId(userId)
                        .accessToken(accessToken)
                        .build();
                userRepository.save(user);
            }
        }
        return SUCCESS;
    }

//    private Long getIdFromRespsonse(KakaoTokenResponse kakaoTokenResponse){
//        return Optional.ofNullable(kakaoTokenResponse.getId())
//                .orElse(NotFoundKakaoId);
//    }
//
//    private KakaoTokenResponse requestKakaoToken(String accessToken){
//        HttpHeaders headers = new HttpHeaders();
//        headers.setBearerAuth(accessToken);
//        HttpEntity<KakaoTokenResponse> kakaoEntity = restTemplate.exchange(baseUrl + tokenUrl, HttpMethod.GET, new HttpEntity<>(headers), KakaoTokenResponse.class);
//        return kakaoEntity.getBody();
//    }

    public KakaoUserResponse getUserInfoFromKakao(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);
        HttpEntity<KakaoUserResponse> kakaoEntity = restTemplate.exchange(baseUrl + userUrl, HttpMethod.POST, new HttpEntity<>(headers), KakaoUserResponse.class);
        System.out.println(kakaoEntity);
        return kakaoEntity.getBody();
    }

    @Override
    public long requestUserid(String accessToken) {
        KakaoUserResponse kakaoUserResponse = getUserInfoFromKakao(accessToken);
        return kakaoUserResponse.getId();
    }

    public KakaoSSOServiceImpl(RestTemplate restTemplate, UserRepository userRepository) {
        this.restTemplate = restTemplate;
        this.userRepository = userRepository;
    }
}
