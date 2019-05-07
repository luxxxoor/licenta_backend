package com.licenta.usm.ExceptionHandeling;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.List;

@AllArgsConstructor
@Getter
public class ApiError {

    private HttpStatus status;
    private String message;
    private List<String> errors;
}