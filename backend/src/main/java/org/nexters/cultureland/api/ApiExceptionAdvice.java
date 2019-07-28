package org.nexters.cultureland.api;

import org.nexters.cultureland.common.ResponseMessage;
import org.nexters.cultureland.common.excepion.NotFoundResouceException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ApiExceptionAdvice {

    @ExceptionHandler({NotFoundResouceException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseMessage handleNotFoundException(NotFoundResouceException exception) {
        ResponseMessage responseMessage = new ResponseMessage(exception.getMessage(), 400, null);

        return responseMessage;
    }
}
