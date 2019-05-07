package com.licenta.usm.ExceptionHandeling;


import com.licenta.usm.Exceptions.AlreadyExistingUserException;
import com.licenta.usm.Exceptions.PasswordTooShortException;
import com.licenta.usm.Exceptions.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
@Slf4j
public class ExceptionHandlerController {
    @ExceptionHandler(AlreadyExistingUserException.class)
    public ResponseEntity<Object> handleAlreadyExistingUser(final AlreadyExistingUserException ex, final WebRequest request) {
        final var error = "There already is an user with the requested name.";
        return createResponseEntity(FORBIDDEN, ex.getLocalizedMessage(), List.of(error));
    }

    @ExceptionHandler(PasswordTooShortException.class)
    public ResponseEntity<Object> handlePasswordTooShort(final PasswordTooShortException ex, final WebRequest request) {
        final String errorSource = ex.getClass().getName();
        final var errorMessage = "Password has to be at least 5 characters long.";
        return createResponseEntity(BAD_REQUEST, ex.getLocalizedMessage(), List.of(errorSource, errorMessage));
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleUserNotFound(final UserNotFoundException ex, final WebRequest request) {
        final var error = "Requested user is not found.";
        return createResponseEntity(NOT_FOUND, ex.getLocalizedMessage(), List.of(error));
    }

    private ResponseEntity<Object> createResponseEntity(final HttpStatus notFound, final String localizedMessage, final List<String> error) {
        final var apiError = new ApiError(notFound, localizedMessage, error);
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }
}
