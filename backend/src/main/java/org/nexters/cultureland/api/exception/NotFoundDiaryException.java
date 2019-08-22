package org.nexters.cultureland.api.exception;

import org.nexters.cultureland.common.excepion.NotFoundResouceException;

public class NotFoundDiaryException extends NotFoundResouceException {
    public NotFoundDiaryException(final String message) {
        super(message);
    }
}
