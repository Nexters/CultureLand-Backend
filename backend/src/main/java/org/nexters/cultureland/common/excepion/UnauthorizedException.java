package org.nexters.cultureland.common.excepion;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/*
    401 UNAUTHORIZED
 */
public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException(String message){
        super(message);
    }
}
