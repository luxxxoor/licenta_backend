package com.licenta.ogm.Exceptions;

import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ResponseStatus(code = NOT_FOUND)
@NoArgsConstructor
public class SubscriptionNotFoundException extends Exception {
    public SubscriptionNotFoundException(final String message) {
        super(message);
    }
}