package org.nexters.cultureland.exception;

import org.nexters.cultureland.common.excepion.NotFoundResouceException;

public class UserNotFoundException extends NotFoundResouceException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
