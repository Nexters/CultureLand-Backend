package org.nexters.cultureland.api.user.service;

public interface SSOService {
    String signInOrSignUp(String accessToken);
    long requestUserid(String accessToken);
}
