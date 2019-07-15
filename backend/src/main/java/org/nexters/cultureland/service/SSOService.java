package org.nexters.cultureland.service;

import org.nexters.cultureland.common.KakaoTokenResponse;
import org.springframework.stereotype.Service;

@Service
public interface SSOService {
    boolean singInOrSignUp(String accessToken, String userId);
}
