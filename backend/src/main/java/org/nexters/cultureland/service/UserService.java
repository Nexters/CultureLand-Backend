package org.nexters.cultureland.service;

import org.nexters.cultureland.model.User;

public interface UserService {
    User findUserbyuserId(long userId);
    void deleteUserbyId(long userId);
}
