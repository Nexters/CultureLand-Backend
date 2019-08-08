package org.nexters.cultureland.api.service.impl;

import org.nexters.cultureland.api.dto.DibsDto;
import org.nexters.cultureland.api.dto.UserDto;
import org.nexters.cultureland.api.exception.UserNotFoundException;
import org.nexters.cultureland.api.model.Dibs;
import org.nexters.cultureland.api.model.User;
import org.nexters.cultureland.api.repo.DibsRepository;
import org.nexters.cultureland.api.repo.UserRepository;
import org.nexters.cultureland.api.service.UserService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private DibsRepository dibsRepository;
    @Override
    public UserDto findUserbyuserId(long userId) {
        userExist(userId);
        User user = this.findUser(userId);
        return new UserDto(user);
    }

    @Transactional
    @Override
    public void deleteUserbyId(long userId) {
        userExist(userId);
        userRepository.deleteByuserId(userId);
    }

    @Transactional
    @Override
    public void addUserDibs(long userId, DibsDto dibsDto) {
        userExist(userId);
        User user = this.findUser(userId);

        Dibs dibs = Dibs.builder()
                .endDate(dibsDto.getEndDate())
                .imageUrl(dibsDto.getImageUrl())
                .place(dibsDto.getPlace())
                .startDate(dibsDto.getStartDate())
                .title(dibsDto.getTitle())
                .user(user)
                .build();
        user.addDibsCulture(dibs);

        userRepository.save(user);
        dibsRepository.save(dibs);
    }

    @Override
    public List<DibsDto> findAllDibs(long userId){
        userExist(userId);
        User user = this.findUser(userId);
        List<DibsDto> dibsDtos = new ArrayList<>();
        List<Dibs> userDibses = user.getDibses();
        for(Dibs dibs : userDibses){
            dibsDtos.add(new DibsDto(dibs));
        }
        return dibsDtos;
    }
    private void userExist(long userId){
        boolean existUser = userRepository.existsByuserId(userId);
        if(!existUser) throw new UserNotFoundException("YOUR ID IS NOT FOUND");
    }
    private User findUser(long userId){
        return userRepository.findByuserId(userId)
                .orElseThrow(() -> new UserNotFoundException("USER NOT FOUND"));
    }
    public UserServiceImpl(UserRepository userRepository, DibsRepository dibsRepository) {
        this.userRepository = userRepository;
        this.dibsRepository = dibsRepository;
    }
}
