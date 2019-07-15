package org.nexters.cultureland.service;

import org.nexters.cultureland.common.KakaoTokenResponse;
import org.nexters.cultureland.model.User;
import org.nexters.cultureland.repo.UserRepository;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class KakaoSSOServiceImpl implements SSOService {
    private final String url = "https://kapi.kakao.com/v1/user/access_token_info";

    private RestTemplate restTemplate;
    private UserRepository userRepository;

    public KakaoSSOServiceImpl(RestTemplate restTemplate, UserRepository userRepository) {
        this.restTemplate = restTemplate;
        this.userRepository = userRepository;
    }

    @Override
    public boolean singInOrSignUp(String accessToken, String currentUserId) {
        KakaoTokenResponse kakaoResponse = getAccessToken(accessToken);

        Long requestUserId = Optional.ofNullable(kakaoResponse.getId())
                .orElse(-1L);

        if(requestUserId == -1L //401 처리
            || requestUserId != Long.parseLong(currentUserId.split("\\s")[1])) return false; // 403 처리

        boolean userExists = userRepository.existsByuserId(currentUserId);
        if(!userExists) {
            User user = User.builder()
                    .userId(currentUserId)
                    .accessToken(accessToken)
                    .build();
            userRepository.save(user);
        }
        return true;
    }

    private KakaoTokenResponse getAccessToken(String accessToken){
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);
        HttpEntity<KakaoTokenResponse> kakaoEntity = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(headers), KakaoTokenResponse.class);
        return kakaoEntity.getBody();
    }
}
