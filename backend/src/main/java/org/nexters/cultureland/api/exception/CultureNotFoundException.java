package org.nexters.cultureland.api.exception;

import org.nexters.cultureland.common.excepion.NotFoundResouceException;

public class CultureNotFoundException extends NotFoundResouceException {
    public CultureNotFoundException(String message) {
        super(message);
    }
}
