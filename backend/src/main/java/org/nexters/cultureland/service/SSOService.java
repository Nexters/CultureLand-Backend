package org.nexters.cultureland.service;

public interface SSOService {
    boolean singInOrSignUp(String accessToken, String userId);
}
