package org.nexters.cultureland.api.exception;

import org.nexters.cultureland.common.excepion.NotFoundResouceException;

public class AccessTokenNotFoundException extends NotFoundResouceException {
    public AccessTokenNotFoundException(String message) {
        super(message);
    }
}