package org.nexters.cultureland.api.controller;

import org.nexters.cultureland.api.dto.WishListDto;
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

    /**
     * /users
     * 유저정보 조회
     * @param userId
     * @return
     */
    @GetMapping
    public ResponseEntity<ResponseMessage> requestUserInfos(@LoginUser long userId) {
        log.info("Call user information params {" + userId + "}");
        ResponseMessage resp = ResponseMessage.getOkResponseMessage();
        resp.setMessage(userService.findUserbyuserId(userId));
//        resp.setPath(request.getServletPath());
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    /**
     * /users/:userId
     * 유저정보 삭제
     * @param userId
     * @return
     */
    @DeleteMapping
    public ResponseEntity<ResponseMessage> deleteUserInfos(@LoginUser long userId) {
        log.info("Call delete user information params {" + userId + "}");
        userService.deleteUserbyId(userId);
        ResponseMessage resp = ResponseMessage.getOkResponseMessage();
//        resp.setPath(request.getServletPath());
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    /**
     * /users/wishList
     * wish list 전체 조회
     * @param userId
     * @return
     */
    @GetMapping(value = "/wishList")
    public ResponseMessage allDibsCultures(@LoginUser long userId) {
        log.info("Call delete user information params {" + userId + "}");
        ResponseMessage responseMessage = new ResponseMessage();
        List<WishListDto> wishListDtos = userService.findAllWishList(userId);
        responseMessage.setMessage(wishListDtos);
        return responseMessage;
    }

    /**
     * /users/wishList
     * wish list 등록
     * @param userId
     * @param cultureInfoId
     * @return
     */
    @PostMapping(value = "/wishList")
    public ResponseMessage addUserDibs(@LoginUser long userId, @RequestParam long cultureInfoId){
        log.info(userId + " " + cultureInfoId);
        ResponseMessage responseMessage = ResponseMessage.getOkResponseMessage();
        userService.addUserWishList(userId, cultureInfoId);
        responseMessage.setMessage("SUCCESS");
        return responseMessage;
    }

    /**
     * /users/wishList/:wishListId
     * wish list 삭제
     * @param userId
     * @param wishListId
     * @return
     */
    @DeleteMapping(value = "/wishList/{wishListId}")
    public ResponseMessage deleteUserDibs(@LoginUser long userId, @PathVariable long wishListId) {
        log.info(userId + " " + wishListId);
        ResponseMessage responseMessage = ResponseMessage.getOkResponseMessage();
        userService.deleteUserWishList(userId, wishListId);
        responseMessage.setMessage("SUCCESS");
        return responseMessage;
    }
}
