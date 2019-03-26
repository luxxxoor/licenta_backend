package com.licenta.emm.Exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@ResponseStatus(code = INTERNAL_SERVER_ERROR, reason = "Could not send email !")
public class EmailNotSent extends Exception {
}
