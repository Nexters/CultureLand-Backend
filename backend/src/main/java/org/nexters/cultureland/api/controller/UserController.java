package org.nexters.cultureland.api.controller;

import org.nexters.cultureland.api.dto.DibsDto;
import org.nexters.cultureland.api.service.UserService;
import org.nexters.cultureland.common.LoginUser;
import org.nexters.cultureland.common.ResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

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
    public ResponseMessage allDibsCultures(@LoginUser long userId) {
        log.info("Call delete user information params {" + userId + "}");
        ResponseMessage responseMessage = new ResponseMessage();
        List<DibsDto> dibsDtos = userService.findAllDibs(userId);
        responseMessage.setMessage(dibsDtos);
        return responseMessage;
    }

    @GetMapping(value = "/dibs/{dibsId}")
    public ResponseMessage allDibsCultures(@LoginUser long userId, @PathVariable long dibsId) {
        log.info("Call delete user information params {" + userId + "}");
        ResponseMessage responseMessage = new ResponseMessage();
        DibsDto dibsDto = userService.findDibsDetail(userId, dibsId);
        responseMessage.setMessage(dibsDto);
        return responseMessage;
    }

    @PostMapping(value = "/dibs")
    public ResponseMessage addUserDibs(@LoginUser long userId, @RequestBody DibsDto dibsDto) {
        log.info(dibsDto.toString());
        ResponseMessage responseMessage = ResponseMessage.getOkResponseMessage();
        userService.addUserDibs(userId, dibsDto);
        responseMessage.setMessage("SUCCESS");
        return responseMessage;
    }

    @DeleteMapping(value = "/dibs/{dibsId}")
    public ResponseMessage deleteUserDibs(@LoginUser long userId, @PathVariable long dibsId) {
        log.info(userId + " " + dibsId);
        ResponseMessage responseMessage = ResponseMessage.getOkResponseMessage();
        userService.deleteUserDibs(userId, dibsId);
        responseMessage.setMessage("SUCCESS");
        return responseMessage;
    }
}
