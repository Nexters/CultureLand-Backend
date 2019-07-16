package org.nexters.cultureland.service;

import org.springframework.stereotype.Service;

@Service
public interface SSOService {
    boolean singInOrSignUp(String accessToken, String userId);
}
