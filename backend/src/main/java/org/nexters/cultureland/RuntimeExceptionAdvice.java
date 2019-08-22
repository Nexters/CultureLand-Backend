package org.nexters.cultureland;

import org.nexters.cultureland.common.ResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@Order(Ordered.LOWEST_PRECEDENCE)
public class RuntimeExceptionAdvice {
    private static final Logger log = LoggerFactory.getLogger(RuntimeExceptionAdvice.class);

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ResponseMessage internalServerError(HttpServletRequest request, Exception e){
        log.error(e.getMessage());
        return new ResponseMessage("SERVER ERROR, Plz retry again later", HttpStatus.INTERNAL_SERVER_ERROR.value(),
                null, request.getServletPath());
    }
}
