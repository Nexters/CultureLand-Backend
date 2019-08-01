package org.nexters.cultureland.api.user.service.impl;

import org.nexters.cultureland.api.user.exception.UserNotFoundException;
import org.nexters.cultureland.api.user.model.User;
import org.nexters.cultureland.api.user.repo.UserRepository;
import org.nexters.cultureland.api.user.service.UserService;
import org.nexters.cultureland.common.excepion.BadRequestException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    @Override
    public User findUserbyuserId(long userId) {
        boolean existUser = userRepository.existsByuserId(userId);
        if(!existUser) throw new BadRequestException("YOUR ID IS NOT FOUND!");

        return userRepository.findByuserId(userId)
                .orElseThrow(() -> new UserNotFoundException("USER NOT FOUND"));
    }

    @Transactional
    @Override
    public void deleteUserbyId(long userId) {
        boolean existUser = userRepository.existsByuserId(userId);
        if(!existUser) throw new BadRequestException("YOUR ID IS NOT FOUND");

        userRepository.deleteByuserId(userId);
    }

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
