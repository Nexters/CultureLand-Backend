package org.nexters.cultureland.api.service.impl;


import org.nexters.cultureland.api.model.User;
import org.nexters.cultureland.api.repo.UserRepository;
import org.nexters.cultureland.api.response.GoogleUserResponse;
import org.nexters.cultureland.api.service.SSOService;
import org.nexters.cultureland.api.service.UserService;
import org.nexters.cultureland.common.JwtManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;

@Service
public class GoogleSSOServiceImpl implements SSOService {
    private static final Logger log = LoggerFactory.getLogger(FacebookSSOServiceImpl.class);
    private final static String baseUrl = "https://www.googleapis.com";
    private final static String userUrl = "/oauth2/v2/tokeninfo";
    private RestTemplate restTemplate;
    private UserService userService;
    private JwtManager jwtManager;

    public GoogleSSOServiceImpl(RestTemplate restTemplate, UserService userService, JwtManager jwtManager) {
        this.restTemplate = restTemplate;
        this.userService = userService;
        this.jwtManager = jwtManager;
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

    @Override
    public long requestUserid(String accessToken) {
        GoogleUserResponse googleUserResponse = this.getUserInfoFromGoogle(accessToken);
        return Long.parseLong(googleUserResponse.getUser_id().substring(2));
    }

    private GoogleUserResponse getUserInfoFromGoogle(String accessToken){
        log.info("Request user Information from Google - " + baseUrl + userUrl);
        String requestUrl = baseUrl + userUrl + "?access_token=" + accessToken;
        HttpEntity<GoogleUserResponse> googleUserResponse = restTemplate.getForEntity(requestUrl, GoogleUserResponse.class);
        log.info("Response user information from Google - " + googleUserResponse);
        return googleUserResponse.getBody();
    }
}
