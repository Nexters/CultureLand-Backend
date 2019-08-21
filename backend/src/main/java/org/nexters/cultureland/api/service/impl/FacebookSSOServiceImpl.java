package org.nexters.cultureland.api.service.impl;

import org.nexters.cultureland.api.dto.SignDto;
import org.nexters.cultureland.api.model.User;
import org.nexters.cultureland.api.response.FacebookUserResponse;
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
public class FacebookSSOServiceImpl implements SSOService {
    private static final Logger log = LoggerFactory.getLogger(FacebookSSOServiceImpl.class);
    private String baseUrl = "https://graph.facebook.com";
    private String userUrl = "/me?fields=name";
    private RestTemplate restTemplate;
    private UserService userService;
    private JwtManager jwtManager;

    public FacebookSSOServiceImpl(RestTemplate restTemplate, UserService userService, JwtManager jwtManager) {
        this.jwtManager = jwtManager;
        this.restTemplate = restTemplate;
        this.userService = userService;
    }

    @Transactional
    @Override
    public SignDto signInOrSignUp(String accessToken) {
        FacebookUserResponse userResponse = getUserInfoFromFacebook(accessToken);
        User user = null;
        synchronized (this) {
            user = userService.createUser(userResponse.getId(), userResponse.getName());
            return new SignDto(jwtManager.makeJwt(user), user.getUserName());
        }
    }

    private FacebookUserResponse getUserInfoFromFacebook(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);
        log.info("Request user Information from Facebook - " + baseUrl + userUrl);
        HttpEntity<FacebookUserResponse> facebookResponse = restTemplate.exchange(baseUrl + userUrl, HttpMethod.GET, new HttpEntity<>(headers), FacebookUserResponse.class);
        log.info("Response user information from Facebook - " + facebookResponse);
        return facebookResponse.getBody();
    }
}