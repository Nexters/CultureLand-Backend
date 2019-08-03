package org.nexters.cultureland.api;

import io.jsonwebtoken.ExpiredJwtException;
import org.nexters.cultureland.api.user.exception.UserNotFoundException;
import org.nexters.cultureland.common.ResponseMessage;
import org.nexters.cultureland.common.excepion.ForbiddenException;
import org.nexters.cultureland.common.excepion.NotFoundResouceException;
import org.nexters.cultureland.common.excepion.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ApiExceptionAdvice {

    @ExceptionHandler({NotFoundResouceException.class, UserNotFoundException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseMessage handleNotFoundException(NotFoundResouceException exception) {
        ResponseMessage responseMessage = new ResponseMessage(exception.getMessage(), 400, null,null);

        return responseMessage;
    }

    @ExceptionHandler({ExpiredJwtException.class})
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ResponseBody
    public ResponseMessage ForbiddenException(HttpServletRequest request, ExpiredJwtException e){
        //String error, int code, String message, String path
        System.out.println(e.getMessage());
        return new ResponseMessage(e.getMessage(), HttpStatus.FORBIDDEN.value(),
                null, request.getServletPath());
    }

    @ExceptionHandler({UnauthorizedException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public ResponseMessage unAuthorizedException(HttpServletRequest request, UnauthorizedException e){
        //String error, int code, String message, String path
        System.out.println(e.getMessage());
        return new ResponseMessage(e.getMessage(), HttpStatus.FORBIDDEN.value(),
                null, request.getServletPath());
    }

    @ExceptionHandler({ForbiddenException.class})
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ResponseBody
    public ResponseMessage forbiddenException(HttpServletRequest request, ForbiddenException e){
        //String error, int code, String message, String path
        System.out.println(e.getMessage());
        return new ResponseMessage(e.getMessage(), HttpStatus.FORBIDDEN.value(),
                null, request.getServletPath());
    }
}
