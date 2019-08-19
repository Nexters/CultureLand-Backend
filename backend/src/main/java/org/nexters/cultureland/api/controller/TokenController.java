package org.nexters.cultureland.api.controller;

import org.nexters.cultureland.api.exception.AccessTokenNotFoundException;
import org.nexters.cultureland.api.service.SSOService;
import org.nexters.cultureland.api.service.impl.FacebookSSOServiceImpl;
import org.nexters.cultureland.api.service.impl.GoogleSSOServiceImpl;
import org.nexters.cultureland.api.service.impl.KakaoSSOServiceImpl;
import org.nexters.cultureland.common.ResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger log = LoggerFactory.getLogger(TokenController.class);
    private ApplicationContext appContext;
    private SSOService ssoService;

    public TokenController(ApplicationContext appContext) {
        this.appContext = appContext;
    }

    @PostMapping
    public ResponseEntity<ResponseMessage> signInorSignUp(@RequestParam String snsName,
                                                          @RequestBody Map<String, String> req,
                                                          HttpServletRequest request) {
        String accessToken = req.get("accessToken");
        log.info("sign in params {" + accessToken + "}");
        if (accessToken == null) {
            throw new AccessTokenNotFoundException("No AccessToken");
        }
        ssoService = this.getSSOService(snsName);
        String jwtToken = ssoService.signInOrSignUp(accessToken);
        ResponseMessage resp = ResponseMessage.getOkResponseMessage();
        resp.setMessage(jwtToken);
        resp.setPath(request.getServletPath());
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    public SSOService getSSOService(String snsName) {
        SSOService ssoService = null;
        switch (snsName.toUpperCase()) {
            case "KAKAO":
                ssoService = appContext.getBean(KakaoSSOServiceImpl.class);
                break;
            case "FACEBOOK":
                ssoService = appContext.getBean(FacebookSSOServiceImpl.class);
                break;
            case "GOOGLE":
                ssoService = appContext.getBean(GoogleSSOServiceImpl.class);
                break;
        }
        return ssoService;
    }
}
