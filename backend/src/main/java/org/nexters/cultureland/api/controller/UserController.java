package org.nexters.cultureland.api.controller;

import org.nexters.cultureland.api.dto.WishListDto;
import org.nexters.cultureland.api.service.UserService;
import org.nexters.cultureland.api.service.WishListService;
import org.nexters.cultureland.common.LoginUser;
import org.nexters.cultureland.common.ResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {
    private final UserService userService;
    private final WishListService wishListService;

    public UserController(final UserService userService, final WishListService wishListService) {
        this.userService = userService;
        this.wishListService = wishListService;
    }

    /**
     * /users
     * 유저정보 조회
     *
     * @param userId
     * @return
     */
    @GetMapping
    public ResponseEntity<ResponseMessage> requestUserInfos(@LoginUser long userId) {
        ResponseMessage resp = ResponseMessage.getOkResponseMessage();
        resp.setMessage(userService.findUserbyuserId(userId));
//        resp.setPath(request.getServletPath());
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    /**
     * /users/:userId
     * 유저정보 삭제
     *
     * @param userId
     * @return
     */
    @DeleteMapping
    public ResponseEntity<ResponseMessage> deleteUserInfos(@LoginUser long userId) {
        userService.deleteUserbyId(userId);
        ResponseMessage resp = ResponseMessage.getOkResponseMessage();
//        resp.setPath(request.getServletPath());
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    /**
     * /users/wishList
     * wish list 전체 조회
     *
     * @param userId
     * @return
     */
    @GetMapping(value = "/wishList")
    public ResponseMessage getAllWishlist(@LoginUser long userId) {
        ResponseMessage responseMessage = new ResponseMessage();
        List<WishListDto> wishListDtos = userService.findAllWishList(userId);
        responseMessage.setMessage(wishListDtos);
        return responseMessage;
    }

    /**
     * /users/wishList?cultureInfoId={}
     * wishList 여부확인
     *
     * @param userId
     * @param cultureInfoId
     * @return
     */
    @GetMapping(value = "/wishList/find")
    public ResponseMessage isMyWishList(@LoginUser long userId, @RequestParam long cultureInfoId) {
        ResponseMessage responseMessage = ResponseMessage.getOkResponseMessage();
        boolean isMyWishList = wishListService.isWishlistByCultureInfoId(userId, cultureInfoId);
//        userService.addUserWishList(userId, cultureInfoId);
        HashMap<String, Boolean> returnValue = new HashMap<>();
        returnValue.put("isMyWishList", isMyWishList);
        responseMessage.setMessage(returnValue);

        return responseMessage;
    }

    /**
     * /users/wishList?cultureInfoId={}
     * wish list 등록
     *
     * @param userId
     * @param cultureInfoId
     * @return
     */
    @PostMapping(value = "/wishList")
    public ResponseMessage addUserWishlist(@LoginUser long userId, @RequestParam long cultureInfoId) {
        ResponseMessage responseMessage = ResponseMessage.getOkResponseMessage();
        userService.addUserWishList(userId, cultureInfoId);
        responseMessage.setMessage("SUCCESS");
        return responseMessage;
    }

    /**
     * /users/wishList/:wishListId
     * wish list 삭제
     *
     * @param userId
     * @param wishListId
     * @return
     */
    @DeleteMapping(value = "/wishList/{wishListId}")
    public ResponseMessage deleteUserWishlist(@LoginUser long userId, @PathVariable long wishListId) {
        ResponseMessage responseMessage = ResponseMessage.getOkResponseMessage();
        userService.deleteUserWishList(userId, wishListId);
        responseMessage.setMessage("SUCCESS");
        return responseMessage;
    }
}
