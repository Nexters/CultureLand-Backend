package org.nexters.cultureland.api.user.controller;

import org.nexters.cultureland.api.user.dto.UserDto;
import org.nexters.cultureland.api.user.service.UserService;
import org.nexters.cultureland.common.ResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/users")
public class UserController {
    private UserService userService;

    @GetMapping
    public ResponseEntity<ResponseMessage> requestUserInfos(HttpServletRequest request){
        long userId = (long) request.getAttribute("userId");
        UserDto user = userService.findUserbyuserId(userId);
        ResponseMessage resp = ResponseMessage.getOkResponseMessage();
        resp.setMessage(user);
        resp.setPath(request.getServletPath());
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<ResponseMessage> deleteUserInfos(HttpServletRequest request){
        long userId = (long) request.getAttribute("userId");
        userService.deleteUserbyId(userId);
        ResponseMessage resp = ResponseMessage.getOkResponseMessage();
        resp.setPath(request.getServletPath());
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    public UserController(UserService userService) {
        this.userService = userService;
    }
}
