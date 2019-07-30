package org.nexters.cultureland.api.user.controller;

import org.nexters.cultureland.api.user.service.SSOService;
import org.nexters.cultureland.api.user.service.impl.FacebookSSOServiceImpl;
import org.nexters.cultureland.api.user.service.impl.KakaoSSOServiceImpl;
import org.nexters.cultureland.common.ResponseMessage;
import org.nexters.cultureland.common.excepion.BadRequestException;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/signInOrUp")
public class TokenController {
    private ApplicationContext appContext;
    private SSOService ssoService;

    @PostMapping
    public ResponseEntity<ResponseMessage> signInorSignUp(@RequestParam String snsName,
                                                          @RequestBody Map<String, String> req){
        String accessToken = req.get("accessToken");
        if(accessToken == null) {throw new BadRequestException("No AccessToken");}
        ssoService = this.getSSOService(snsName);
        String jwtToken = ssoService.signInOrSignUp(accessToken);
        ResponseMessage resp = ResponseMessage.getOkResponseMessage();
        resp.setMessage(jwtToken);
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

    public TokenController(ApplicationContext appContext) {
        this.appContext = appContext;
    }
}
