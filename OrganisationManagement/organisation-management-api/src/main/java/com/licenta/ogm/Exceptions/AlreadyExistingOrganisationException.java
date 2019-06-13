package com.licenta.ogm.Exceptions;

import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.FORBIDDEN;

@ResponseStatus(code = FORBIDDEN)
@NoArgsConstructor
public class AlreadyExistingOrganisationException extends Exception {
    public AlreadyExistingOrganisationException(final String message) {
        super(message);
    }
}
