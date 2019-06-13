package com.licenta.ogm.ExceptionHandling;

import com.licenta.ogm.Exceptions.AlreadyExistingOrganisationException;
import com.licenta.ogm.Exceptions.InvalidPasswordException;
import com.licenta.ogm.Exceptions.OrganisationNotFoundException;
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
    @ExceptionHandler(AlreadyExistingOrganisationException.class)
    public ResponseEntity<Object> handleAlreadyExistingUser(final AlreadyExistingOrganisationException ex,
                                                            final WebRequest request) {
        final String errorSource = ex.getClass().getName();
        final var errorMessage = "There already is an organisation with the requested name.";
        return createResponseEntity(FORBIDDEN, ex.getLocalizedMessage(), List.of(errorSource, errorMessage));
    }

    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<Object> handlePasswordTooShort(final InvalidPasswordException ex, final WebRequest request) {
        final String errorSource = ex.getClass().getName();
        final var errorMessage = "Password has to be at least 5 characters long.";
        return createResponseEntity(BAD_REQUEST, ex.getLocalizedMessage(), List.of(errorSource, errorMessage));
    }

    @ExceptionHandler(OrganisationNotFoundException.class)
    public ResponseEntity<Object> handleUserNotFound(final OrganisationNotFoundException ex, final WebRequest request) {
        final String errorSource = ex.getClass().getName();
        final var errorMessage = "Requested organisation is not found.";
        return createResponseEntity(NOT_FOUND, ex.getLocalizedMessage(), List.of(errorSource, errorMessage));
    }

    private ResponseEntity<Object> createResponseEntity(final HttpStatus notFound, final String localizedMessage,
                                                        final List<String> error) {
        final var apiError = new ApiError(notFound, localizedMessage, error);
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }
}
