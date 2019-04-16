package com.licenta.usm.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.LENGTH_REQUIRED;

@ResponseStatus(code = new HttpStatus(600, ""))
public class PasswordTooShortException extends Exception {
}