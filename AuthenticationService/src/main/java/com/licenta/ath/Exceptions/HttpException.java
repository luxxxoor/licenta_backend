package com.licenta.ath.Exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Setter
@Getter
public class HttpException extends RuntimeException {

    private HttpStatus httpStatus;

    public HttpException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public HttpException(HttpStatus httpStatus) {
        this("", httpStatus);
    }
}