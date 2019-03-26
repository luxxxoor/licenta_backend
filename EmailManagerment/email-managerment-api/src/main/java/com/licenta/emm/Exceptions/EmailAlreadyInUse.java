package com.licenta.emm.Exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.FORBIDDEN;

@ResponseStatus(code = FORBIDDEN, reason = "Email Already In Use")
public class EmailAlreadyInUse extends Exception{
}
