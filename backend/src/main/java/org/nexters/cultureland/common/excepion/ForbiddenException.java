package org.nexters.cultureland.common.excepion;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/*
    403 FORBIDDEN
 */
public class ForbiddenException extends RuntimeException {
    public ForbiddenException(String message){
        super(message);
    }
}
