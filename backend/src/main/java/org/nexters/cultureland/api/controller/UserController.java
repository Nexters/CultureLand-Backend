package org.nexters.cultureland.api.controller;

import lombok.extern.java.Log;
import org.nexters.cultureland.api.dto.DibsDto;
import org.nexters.cultureland.api.service.UserService;
import org.nexters.cultureland.common.ResponseMessage;
import org.nexters.cultureland.common.LoginUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {
    private UserService userService;
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @GetMapping
    public ResponseEntity<ResponseMessage> requestUserInfos(@LoginUser long userId) {
        log.info("Call user information params {" + userId + "}");
        ResponseMessage resp = ResponseMessage.getOkResponseMessage();
        resp.setMessage(userService.findUserbyuserId(userId));
//        resp.setPath(request.getServletPath());
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<ResponseMessage> deleteUserInfos(@LoginUser long userId) {
        log.info("Call delete user information params {" + userId + "}");
        userService.deleteUserbyId(userId);
        ResponseMessage resp = ResponseMessage.getOkResponseMessage();
//        resp.setPath(request.getServletPath());
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @GetMapping(value = "/dibs")
    public ResponseMessage allDibsCultures(@LoginUser long userId){
        log.info("Call delete user information params {" + userId + "}");
        ResponseMessage responseMessage = new ResponseMessage();
        List<DibsDto> dibsDtos = userService.findAllDibs(userId);
        responseMessage.setMessage(dibsDtos);
        return responseMessage;
    }

    @PostMapping(value = "/dibs")
    public ResponseMessage addUserDibs(@LoginUser long userId, @RequestBody DibsDto dibsDto){
        log.info(dibsDto.toString());
        ResponseMessage responseMessage = ResponseMessage.getOkResponseMessage();
        responseMessage.setMessage("SUCCESS");
        return responseMessage;
    }

    public UserController(UserService userService) {
        this.userService = userService;
    }
}
