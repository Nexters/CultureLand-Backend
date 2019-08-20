package org.nexters.cultureland.api.service;

import org.nexters.cultureland.api.dto.SignDto;

public interface SSOService {
    SignDto signInOrSignUp(String accessToken);
}
