package com.licenta.emm.Exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ResponseStatus(code = NOT_FOUND, reason = "Invalid Confirmation Link")
public class InvalidConfirmationLink extends Exception {
}

