package org.nexters.cultureland.api.user.controller;

import org.nexters.cultureland.api.user.model.User;
import org.nexters.cultureland.api.user.service.UserService;
import org.nexters.cultureland.common.ResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/users")
public class UserController {
    private UserService userService;

    @GetMapping
    public ResponseEntity<ResponseMessage> requestUserInfos(HttpServletRequest request){
        long userId = (long) request.getAttribute("userId");
        User user = userService.findUserbyuserId(userId);
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
