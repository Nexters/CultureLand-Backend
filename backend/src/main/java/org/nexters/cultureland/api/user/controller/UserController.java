package org.nexters.cultureland.api.user.controller;

import org.modelmapper.ModelMapper;
import org.nexters.cultureland.api.user.dto.UserDto;
import org.nexters.cultureland.api.user.model.User;
import org.nexters.cultureland.api.user.service.UserService;
import org.nexters.cultureland.common.ResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/users")
public class UserController {
    private UserService userService;
    private ModelMapper modelMapper;

    @GetMapping(value = "/{userId}")
    public ResponseEntity<ResponseMessage> requestUserInfos(@PathVariable Long userId){
        User user = userService.findUserbyuserId(userId);
        UserDto userDto = modelMapper.map(user, UserDto.class);
        ResponseMessage resp = ResponseMessage.getOkResponseMessage();
        resp.setMessage(userDto);

        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{userId}")
    public ResponseEntity<ResponseMessage> deleteUserInfos(@PathVariable Long userId){
        userService.deleteUserbyId(userId);
        ResponseMessage resp = ResponseMessage.getOkResponseMessage();
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }
}
