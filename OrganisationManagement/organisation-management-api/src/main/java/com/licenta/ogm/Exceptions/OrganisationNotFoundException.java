package com.licenta.ogm.Exceptions;

import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ResponseStatus(code = NOT_FOUND)
@NoArgsConstructor
public class OrganisationNotFoundException extends Exception {
    public OrganisationNotFoundException(final String message) {
        super(message);
    }
}
