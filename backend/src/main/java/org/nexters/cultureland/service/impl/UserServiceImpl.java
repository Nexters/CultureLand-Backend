package org.nexters.cultureland.service.impl;

import org.nexters.cultureland.exception.BadRequestException;
import org.nexters.cultureland.model.User;
import org.nexters.cultureland.repo.UserRepository;
import org.nexters.cultureland.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    @Override
    public User findUserbyuserId(long userId) {
        boolean existUser = userRepository.existsByuserId(userId);
        if(!existUser) throw new BadRequestException("YOUR ID IS NOT FOUND!");

        return userRepository.findByuserId(userId);
    }

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
