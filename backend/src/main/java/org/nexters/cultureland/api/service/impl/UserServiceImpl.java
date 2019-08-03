package org.nexters.cultureland.api.service.impl;

import org.nexters.cultureland.api.dto.UserDto;
import org.nexters.cultureland.api.exception.UserNotFoundException;
import org.nexters.cultureland.api.model.User;
import org.nexters.cultureland.api.repo.UserRepository;
import org.nexters.cultureland.api.service.UserService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    @Override
    public UserDto findUserbyuserId(long userId) {
        userExist(userId);
        User user = userRepository.findByuserId(userId)
                .orElseThrow(() -> new UserNotFoundException("USER NOT FOUND"));
        return new UserDto(user);
    }

    @Transactional
    @Override
    public void deleteUserbyId(long userId) {
        userExist(userId);
        userRepository.deleteByuserId(userId);
    }

    private void userExist(long userId){
        boolean existUser = userRepository.existsByuserId(userId);
        if(!existUser) throw new UserNotFoundException("YOUR ID IS NOT FOUND");
    }

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}