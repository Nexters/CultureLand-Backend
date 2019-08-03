package org.nexters.cultureland.exception;

import org.nexters.cultureland.common.excepion.NotFoundResouceException;

public class AccessTokenNotFoundException  extends NotFoundResouceException {
    public AccessTokenNotFoundException(String message) {
        super(message);
    }
}