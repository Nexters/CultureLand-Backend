package org.nexters.cultureland.common.excepion;

/*
    401 UNAUTHORIZED
 */
public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException(String message) {
        super(message);
    }
}
