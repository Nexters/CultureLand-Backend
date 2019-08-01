package org.nexters.cultureland.api.user.service;

import org.nexters.cultureland.api.user.dto.UserDto;

public interface UserService {
    UserDto findUserbyuserId(long userId);
    void deleteUserbyId(long userId);
}
