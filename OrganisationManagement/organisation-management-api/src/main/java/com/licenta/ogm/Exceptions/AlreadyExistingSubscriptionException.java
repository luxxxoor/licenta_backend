package com.licenta.ogm.Exceptions;

import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.FORBIDDEN;

@ResponseStatus(code = FORBIDDEN)
@NoArgsConstructor
public class AlreadyExistingSubscriptionException extends Exception {
    public AlreadyExistingSubscriptionException(final String message) {
        super(message);
    }
}