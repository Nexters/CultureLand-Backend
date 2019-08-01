package org.nexters.cultureland.api.user.exception;

import org.nexters.cultureland.common.excepion.BadRequestException;

public class UserNotFoundException extends BadRequestException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
