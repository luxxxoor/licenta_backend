package com.licenta.ogm.Exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ResponseStatus(code = BAD_REQUEST)
public class InvalidPasswordException extends Exception {
    public InvalidPasswordException(final String message) {
        super(message);
    }
}
