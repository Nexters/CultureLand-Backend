package org.nexters.cultureland.api.user.service;

import org.nexters.cultureland.api.user.model.User;

public interface UserService {
    User findUserbyuserId(long userId);
    void deleteUserbyId(long userId);
}
