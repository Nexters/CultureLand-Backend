package org.nexters.cultureland.api.service;

import org.nexters.cultureland.api.dto.UserDto;

public interface UserService {
    UserDto findUserbyuserId(long userId);
    void deleteUserbyId(long userId);
}
