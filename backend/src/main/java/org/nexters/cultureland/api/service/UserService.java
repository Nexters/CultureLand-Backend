package org.nexters.cultureland.service;

import org.nexters.cultureland.dto.UserDto;

public interface UserService {
    UserDto findUserbyuserId(long userId);
    void deleteUserbyId(long userId);
}
