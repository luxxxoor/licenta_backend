package com.licenta.usm.Exceptions;

import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.FORBIDDEN;

@ResponseStatus(code = FORBIDDEN)
@NoArgsConstructor
public class AlreadyExistingUserException extends Exception {
    public AlreadyExistingUserException(final String message) {
        super(message);
    }
}
