package org.nexters.cultureland.api;

import org.nexters.cultureland.api.diary.excepion.NotFoundResouceException;
import org.nexters.cultureland.api.message.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ApiExceptionAdvice {

    @ExceptionHandler({NotFoundResouceException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ResponseEntity<ErrorMessage> handleNotFoundException(NotFoundResouceException exception) {
        ErrorMessage errorMessage = new ErrorMessage(exception.getMessage());
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }
}