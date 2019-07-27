package org.nexters.cultureland.api.user.service;

public interface SSOService {
    boolean signInOrSignUp(String accessToken);
    long requestUserid(String accessToken);

}
