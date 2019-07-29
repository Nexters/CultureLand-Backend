package org.nexters.cultureland.api.user.service.impl;

import org.nexters.cultureland.api.user.model.User;
import org.nexters.cultureland.api.user.repo.UserRepository;
import org.nexters.cultureland.api.user.response.FacebookUserResponse;
import org.nexters.cultureland.api.user.service.SSOService;
import org.nexters.cultureland.common.JwtServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;

@Service
public class FacebookSSOServiceImpl implements SSOService {
    private String baseUrl = "https://graph.facebook.com";
    private String userUrl = "/me";
    private RestTemplate restTemplate;
    private UserRepository userRepository;
    @Autowired
    private JwtServiceImpl jwtService;

    @Transactional
    @Override
    public String signInOrSignUp(String accessToken) {
        long userId = requestUserid(accessToken);
        User user = null;
        synchronized (this){
            boolean userExists = userRepository.existsByuserId(userId);
            if(!userExists) {
                user = User.builder()
                        .userId(userId)
                        .build();
                userRepository.save(user);
            }
            else{
                user = userRepository.findByuserId(userId);
            }
            return jwtService.makeJwt(user);
        }
    }

    public FacebookUserResponse getUserInfoFromFacebook(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);
        HttpEntity<FacebookUserResponse> facebookResponse = restTemplate.exchange(baseUrl + userUrl, HttpMethod.GET, new HttpEntity<>(headers), FacebookUserResponse.class);
        System.out.println(facebookResponse);
        return facebookResponse.getBody();
    }

    @Override
    public long requestUserid(String accessToken) {
        FacebookUserResponse facebookUserResponse = getUserInfoFromFacebook(accessToken);
        return facebookUserResponse.getId();
    }

    public FacebookSSOServiceImpl(RestTemplate restTemplate, UserRepository userRepository) {
        this.restTemplate = restTemplate;
        this.userRepository = userRepository;
    }
}