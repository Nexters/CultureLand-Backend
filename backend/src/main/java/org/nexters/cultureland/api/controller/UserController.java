package org.nexters.cultureland.controller;

import org.nexters.cultureland.service.UserService;
import org.nexters.cultureland.common.ResponseMessage;
import org.nexters.cultureland.common.LoginUser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/users")
public class UserController {
    private UserService userService;

    @GetMapping
    public ResponseEntity<ResponseMessage> requestUserInfos(@LoginUser long userId){
        ResponseMessage resp = ResponseMessage.getOkResponseMessage();
        resp.setMessage(userService.findUserbyuserId(userId));
//        resp.setPath(request.getServletPath());
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<ResponseMessage> deleteUserInfos(@LoginUser long userId){
        userService.deleteUserbyId(userId);
        ResponseMessage resp = ResponseMessage.getOkResponseMessage();
//        resp.setPath(request.getServletPath());
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    public UserController(UserService userService) {
        this.userService = userService;
    }
}
