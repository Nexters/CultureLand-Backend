package org.nexters.cultureland.api.user.service.impl;

import org.nexters.cultureland.api.user.response.FacebookUserResponse;
import org.nexters.cultureland.api.user.model.User;
import org.nexters.cultureland.api.user.repo.UserRepository;
import org.nexters.cultureland.api.user.service.SSOService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;

@Service
public class FacebookSSOServiceImpl implements SSOService {
    private String baseUrl = "https://graph.facebook.com";
    //    private String tokenUrl = "/v1/user/access_token_info";
    private String userUrl = "/me";
    private boolean SUCCESS = true;
    private RestTemplate restTemplate;
    private UserRepository userRepository;
    @Transactional
    @Override
    public boolean signInOrSignUp(String accessToken) {
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