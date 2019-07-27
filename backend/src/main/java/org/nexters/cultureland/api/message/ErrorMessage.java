package org.nexters.cultureland.api.message;

import lombok.Getter;

@Getter
public class ErrorMessage {
    private final String message;

    public ErrorMessage(final String message) {
        this.message = message;
    }
}
