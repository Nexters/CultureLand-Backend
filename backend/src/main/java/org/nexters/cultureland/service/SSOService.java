package org.nexters.cultureland.service;

public interface SSOService {
    boolean signInOrSignUp(String accessToken);
    long requestUserid(String accessToken);

}
