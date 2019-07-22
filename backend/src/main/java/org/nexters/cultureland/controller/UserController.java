package org.nexters.cultureland.controller;

import org.nexters.cultureland.common.ResponseMessage;
import org.nexters.cultureland.exception.BadRequestException;
import org.nexters.cultureland.model.User;
import org.nexters.cultureland.service.FacebookSSOServiceImpl;
import org.nexters.cultureland.service.KakaoSSOServiceImpl;
import org.nexters.cultureland.service.SSOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private ApplicationContext appContext;

    private SSOService ssoService;

    @PostMapping
    public ResponseEntity<ResponseMessage> signInorSignUp(@RequestParam String snsName,
                                                          @RequestBody User user){
        if(user.getAccessToken() == null) {throw new BadRequestException("No AccessToken or No userId");}
        ssoService = this.getSSOService(snsName);
        boolean signUpSucceed = ssoService.signInOrSignUp(user.getAccessToken());
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
}
