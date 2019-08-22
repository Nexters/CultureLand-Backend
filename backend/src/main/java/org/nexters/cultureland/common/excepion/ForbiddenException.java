package org.nexters.cultureland.common.excepion;

/*
    403 FORBIDDEN
 */
public class ForbiddenException extends RuntimeException {
    public ForbiddenException(String message) {
        super(message);
    }
}
