package org.nexters.cultureland.controller;

import org.nexters.cultureland.common.ResponseMessage;
import org.nexters.cultureland.exception.BadRequestException;
import org.nexters.cultureland.model.User;
import org.nexters.cultureland.service.SSOService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    private SSOService ssoService;

    public UserController(SSOService ssoService) {
        this.ssoService = ssoService;
    }

    @PostMapping
    public ResponseEntity<ResponseMessage> signInorSignUp(@RequestBody User user){
        if(user.getAccessToken() == null) {throw new BadRequestException("No AccessToken or No userId");}
        boolean signUpSucceed = ssoService.signInOrSignUp(user.getAccessToken());
        ResponseMessage resp = ResponseMessage.getOkResponseMessage();
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }
}
