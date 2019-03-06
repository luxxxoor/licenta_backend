package com.licenta.usm.Exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.FORBIDDEN;

@ResponseStatus(code = FORBIDDEN, reason = "Already Existing User")
public class ExistingUserException extends Exception {
}
