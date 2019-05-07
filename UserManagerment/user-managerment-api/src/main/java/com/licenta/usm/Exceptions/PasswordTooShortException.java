package com.licenta.usm.Exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ResponseStatus(code = BAD_REQUEST)
public class PasswordTooShortException extends Exception {
    public PasswordTooShortException(final String message) {
        super(message);
    }
}