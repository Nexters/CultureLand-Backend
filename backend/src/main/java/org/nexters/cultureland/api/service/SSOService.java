package org.nexters.cultureland.api.service;

public interface SSOService {
    String signInOrSignUp(String accessToken);

    long requestUserid(String accessToken);
}
