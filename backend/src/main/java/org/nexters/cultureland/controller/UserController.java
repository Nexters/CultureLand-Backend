package org.nexters.cultureland.controller;

import org.nexters.cultureland.model.User;
import org.nexters.cultureland.service.KakaoSSOServiceImpl;
import org.nexters.cultureland.service.SSOService;
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
    public void signInorSignUp(@RequestBody User user){
        ssoService.singInOrSignUp(user.getAccessToken(), user.getUserId());
    }
}
