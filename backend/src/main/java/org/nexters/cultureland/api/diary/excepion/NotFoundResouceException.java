package org.nexters.cultureland.api.diary.excepion;

public class NotFoundResouceException extends RuntimeException {
    public NotFoundResouceException(final String message) {
        super(message);
    }
}
