package org.nexters.cultureland.api.service.impl;

import org.nexters.cultureland.api.dto.WishListDto;
import org.nexters.cultureland.api.dto.UserDto;
import org.nexters.cultureland.api.exception.DibsNotFoundException;
import org.nexters.cultureland.api.exception.UserNotFoundException;
import org.nexters.cultureland.api.model.WishList;
import org.nexters.cultureland.api.model.User;
import org.nexters.cultureland.api.repo.WishListRepository;
import org.nexters.cultureland.api.repo.UserRepository;
import org.nexters.cultureland.api.service.UserService;
import org.nexters.cultureland.common.excepion.ForbiddenException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private WishListRepository wishListRepository;

    public UserServiceImpl(UserRepository userRepository, WishListRepository wishListRepository) {
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
    public void addUserDibs(long userId, WishListDto wishListDto) {
        userExist(userId);
        User user = this.findUser(userId);

        WishList wishList = WishList.builder()
                .endDate(wishListDto.getEndDate())
                .imageUrl(wishListDto.getImageUrl())
                .place(wishListDto.getPlace())
                .startDate(wishListDto.getStartDate())
                .title(wishListDto.getTitle())
                .user(user)
                .build();
//        user.addDibsCulture(wishList);

        wishListRepository.save(wishList);
    }

    @Transactional
    @Override
    public void deleteUserDibs(long userId, long dibsId) {
        this.userExist(userId);
        User user = this.findUser(userId);

        WishList wishList = wishListRepository.findById(dibsId).orElseThrow(() -> new DibsNotFoundException("찜 목록을 찾을 수 없습니다."));
        if (wishList.getUser().getUserId() != userId) {
            throw new ForbiddenException("권한이 없습니다. 다시 시도해주세요");
        }

        wishListRepository.delete(wishList);
    }

    @Transactional
    @Override
    public WishListDto findDibsDetail(long userId, long dibsId) {
        this.userExist(userId);
        User user = this.findUser(userId);

        WishList wishList = wishListRepository.findById(dibsId).orElseThrow(() -> new DibsNotFoundException("찜 목록을 찾을 수 없습니다."));
        if (wishList.getUser().getUserId() != userId) {
            throw new ForbiddenException("권한이 없습니다. 다시 시도해주세요");
        }
        this.wishListRepository.findByUser(user);
        return new WishListDto(wishList);
    }

    @Override
    public List<WishListDto> findAllDibs(long userId) {
        userExist(userId);
        User user = this.findUser(userId);
        List<WishListDto> wishListDtos = new ArrayList<>();
        List<WishList> userWishLists = user.getWishLists();
        for (WishList wishList : userWishLists) {
            wishListDtos.add(new WishListDto(wishList));
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
