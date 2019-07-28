package org.nexters.cultureland.api.user.handler;

import org.nexters.cultureland.api.user.exception.BadRequestException;
import org.nexters.cultureland.api.user.exception.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

public class RestTemplateErrorHandler implements ResponseErrorHandler {
    @Override
    public boolean hasError(ClientHttpResponse clientHttpResponse) throws IOException {
        final HttpStatus statusCode = clientHttpResponse.getStatusCode();
        //response.getBody() 넘겨 받은 body 값으로 적절한 예외 상태 확인 이후 boolean return
        return !statusCode.is2xxSuccessful();
    }

    @Override
    public void handleError(ClientHttpResponse clientHttpResponse) throws IOException {
        switch (clientHttpResponse.getStatusCode()){
            case BAD_REQUEST:
                throw new BadRequestException("Your request is bad request, please check you token or others");
            case UNAUTHORIZED:
                throw new UnauthorizedException("TOKEN NOT AVAILABLE");
        }
    }
}