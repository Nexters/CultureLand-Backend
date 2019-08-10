package org.nexters.cultureland.api.service.impl;

import org.nexters.cultureland.api.model.User;
import org.nexters.cultureland.api.repo.UserRepository;
import org.nexters.cultureland.api.response.FacebookUserResponse;
import org.nexters.cultureland.api.service.SSOService;
import org.nexters.cultureland.common.JwtManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;

@Service
public class FacebookSSOServiceImpl implements SSOService {
    private static final Logger log = LoggerFactory.getLogger(FacebookSSOServiceImpl.class);
    private String baseUrl = "https://graph.facebook.com";
    private String userUrl = "/me";
    private RestTemplate restTemplate;
    private UserRepository userRepository;
    private JwtManager jwtManager;

    @Transactional
    @Override
    public String signInOrSignUp(String accessToken) {
        long userId = requestUserid(accessToken);
        User user = null;
        synchronized (this) {
            boolean userExists = userRepository.existsByuserId(userId);
            if (!userExists) {
                user = User.builder()
                        .userId(userId)
                        .build();
                userRepository.save(user);
            } else {
                user = userRepository.findByuserId(userId)
                        .orElseThrow(RuntimeException::new);
            }
            return jwtManager.makeJwt(user);
        }
    }

    public FacebookUserResponse getUserInfoFromFacebook(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);
        log.info("Request user Information from Facebook - " + baseUrl + userUrl);
        HttpEntity<FacebookUserResponse> facebookResponse = restTemplate.exchange(baseUrl + userUrl, HttpMethod.GET, new HttpEntity<>(headers), FacebookUserResponse.class);
        log.info("Response user information from Facebook - " + facebookResponse);
        return facebookResponse.getBody();
    }

    @Override
    public long requestUserid(String accessToken) {
        FacebookUserResponse facebookUserResponse = getUserInfoFromFacebook(accessToken);
        return facebookUserResponse.getId();
    }

    public FacebookSSOServiceImpl(RestTemplate restTemplate, UserRepository userRepository, JwtManager jwtManager) {
        this.jwtManager = jwtManager;
        this.restTemplate = restTemplate;
        this.userRepository = userRepository;
    }
}