package org.nexters.cultureland.controller;

import org.nexters.cultureland.common.ResponseMessage;
import org.nexters.cultureland.dto.UserDto;
import org.nexters.cultureland.exception.BadRequestException;
import org.nexters.cultureland.model.User;
import org.nexters.cultureland.service.UserService;
import org.nexters.cultureland.service.impl.FacebookSSOServiceImpl;
import org.nexters.cultureland.service.impl.KakaoSSOServiceImpl;
import org.nexters.cultureland.service.SSOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/users")
public class UserController {
    @Autowired
    private ApplicationContext appContext;

    private SSOService ssoService;
    private UserService userService;

    @PostMapping
    public ResponseEntity<ResponseMessage> signInorSignUp(@RequestParam String snsName,
                                                          @RequestBody UserDto userDto){
        if(userDto.getAccessToken() == null) {throw new BadRequestException("No AccessToken or No userId");}
        ssoService = this.getSSOService(snsName);
        boolean signUpSucceed = ssoService.signInOrSignUp(userDto.getAccessToken());
        ResponseMessage resp = ResponseMessage.getOkResponseMessage();
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @GetMapping(value = "/{userId}")
    public ResponseEntity<ResponseMessage> requestUserInfos(@PathVariable Long userId){
        User user = userService.findUserbyuserId(userId);
        ResponseMessage resp = ResponseMessage.getOkResponseMessage();
        resp.setMessage(user);

        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{userId}")
    public ResponseEntity<ResponseMessage> deleteUserInfos(@PathVariable Long userId){
        userService.deleteUserbyId(userId);
        ResponseMessage resp = ResponseMessage.getOkResponseMessage();
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    public SSOService getSSOService(String snsName) {
        SSOService ssoService = null;
        switch (snsName.toUpperCase()){
            case "KAKAO":
                ssoService = appContext.getBean(KakaoSSOServiceImpl.class);
                break;
            case "FACEBOOK":
                ssoService = appContext.getBean(FacebookSSOServiceImpl.class);
                break;
        }
        return ssoService;
    }

    public UserController(UserService userService) {
        this.userService = userService;
    }
}
