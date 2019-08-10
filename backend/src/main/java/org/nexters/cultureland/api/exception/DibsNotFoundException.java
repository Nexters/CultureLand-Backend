package org.nexters.cultureland.api.exception;

import org.nexters.cultureland.common.excepion.NotFoundResouceException;

public class DibsNotFoundException extends NotFoundResouceException {
    public DibsNotFoundException(String message) {
        super(message);
    }
}
