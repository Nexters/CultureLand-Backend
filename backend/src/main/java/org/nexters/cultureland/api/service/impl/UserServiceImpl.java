package org.nexters.cultureland.api.service.impl;

import org.nexters.cultureland.api.dto.UserDto;
import org.nexters.cultureland.api.dto.WishListDto;
import org.nexters.cultureland.api.exception.CultureNotFoundException;
import org.nexters.cultureland.api.exception.DibsNotFoundException;
import org.nexters.cultureland.api.exception.UserNotFoundException;
import org.nexters.cultureland.api.exception.WishListDuplicationException;
import org.nexters.cultureland.api.model.CultureRawData;
import org.nexters.cultureland.api.model.User;
import org.nexters.cultureland.api.model.WishList;
import org.nexters.cultureland.api.repo.CultureRawRepository;
import org.nexters.cultureland.api.repo.UserRepository;
import org.nexters.cultureland.api.repo.WishListRepository;
import org.nexters.cultureland.api.service.UserService;
import org.nexters.cultureland.common.excepion.ForbiddenException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private WishListRepository wishListRepository;
    private CultureRawRepository rawRepository;

    public UserServiceImpl(UserRepository userRepository, WishListRepository wishListRepository, CultureRawRepository rawRepository) {
        this.rawRepository = rawRepository;
        this.userRepository = userRepository;
        this.wishListRepository = wishListRepository;
    }

    @Transactional
    @Override
    public User createUser(long userId, String userName) {
        User user;
        boolean userExists = userRepository.existsByuserId(userId);
        if (!userExists) {
            user = User.builder()
                    .userId(userId)
                    .userName(userName)
                    .build();
            userRepository.save(user);
        } else {
            user = userRepository.findByuserId(userId)
                    .orElseThrow(RuntimeException::new);
        }
        return user;
    }

    @Override
    public UserDto findUserbyuserId(long userId) {
        this.userExist(userId);
        User user = this.findUser(userId);

        return new UserDto(user);
    }

    @Transactional
    @Override
    public void deleteUserbyId(long userId) {
        this.userExist(userId);
        this.userRepository.deleteByuserId(userId);
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    @Override
    public void addUserWishList(long userId, long cultureInfoId) {
            User user = this.findUser(userId);
            List<WishList> wishLists = this.wishListRepository.findByUser(user);
            for (WishList wishList : wishLists) {
                if (wishList.getCultureRawData().getId() == cultureInfoId) {
                    throw new WishListDuplicationException("중복된 WishList 입니다.");
                }
            }
            CultureRawData cultureRawData = rawRepository.findById(cultureInfoId)
                    .orElseThrow(() -> new CultureNotFoundException("CULTURE DATA NOT FOUND"));

            WishList wishList = new WishList(cultureRawData, user);
            this.wishListRepository.save(wishList);
    }

    @Transactional
    @Override
    public void deleteUserWishList(long userId, long wishListId) {
        this.userExist(userId);
//        User user = this.findUser(userId);
        WishList wishList = wishListRepository.findById(wishListId).orElseThrow(() -> new DibsNotFoundException("위시리스트 목록을 찾을 수 없습니다."));
        if (wishList.getUser().getUserId() != userId) {
            throw new ForbiddenException("권한이 없습니다. 다시 시도해주세요");
        }

        this.wishListRepository.delete(wishList);
    }

    @Override
    public List<WishListDto> findAllWishList(long userId) {
        this.userExist(userId);
        User user = this.findUser(userId);
        List<WishListDto> wishListDtos = new ArrayList<>();
        List<WishList> userWishList = user.getWishLists();
        for (WishList wishList : userWishList) {
            wishListDtos.add(new WishListDto(wishList.getId(), wishList.getCultureRawData()));
        }
        return wishListDtos;
    }

    private void userExist(long userId) {
        boolean existUser = userRepository.existsByuserId(userId);
        if (!existUser) throw new UserNotFoundException("YOUR ID IS NOT FOUND");
    }

    private User findUser(long userId) {
        return userRepository.findByuserId(userId)
                .orElseThrow(() -> new UserNotFoundException("USER NOT FOUND"));
    }
}
