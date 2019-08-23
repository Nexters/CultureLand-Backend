package org.nexters.cultureland.api.exception;

import org.nexters.cultureland.common.excepion.DuplicationException;

public class WishListDuplicationException extends DuplicationException {
    public WishListDuplicationException(String message) {
        super(message);
    }
}
