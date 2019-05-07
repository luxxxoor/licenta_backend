package com.licenta.usm.Exceptions;

import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ResponseStatus(code = NOT_FOUND)
@NoArgsConstructor
public class UserNotFoundException extends Exception {
    public UserNotFoundException(final String message) {
        super(message);
    }
}
