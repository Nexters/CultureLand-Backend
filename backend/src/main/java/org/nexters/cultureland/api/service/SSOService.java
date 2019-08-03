package org.nexters.cultureland.service;

public interface SSOService {
    String signInOrSignUp(String accessToken);
    long requestUserid(String accessToken);
}
