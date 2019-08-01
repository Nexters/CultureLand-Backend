package org.nexters.cultureland.message;

import lombok.Getter;

@Getter
public class ErrorMessage {
    private final String message;

    public ErrorMessage(final String message) {
        this.message = message;
    }
}
