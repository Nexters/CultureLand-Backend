package org.nexters.cultureland.api.service;

import org.nexters.cultureland.api.dto.DibsDto;
import org.nexters.cultureland.api.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto findUserbyuserId(long userId);
    void deleteUserbyId(long userId);
    List<DibsDto> findAllDibs(long userId);
    void addUserDibs(long userId, DibsDto dibsDto);
    void deleteUserDibs(long userId, long dibsId);
    DibsDto findDibsDetail(long userId, long dibsId);
}
