package org.ipan.users.controller;

import jakarta.validation.ConstraintViolationException;
import org.ipan.common.dto.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class UsersExceptionHandler {
    @ExceptionHandler({ConstraintViolationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO handleException(ConstraintViolationException e) {
        String message = e.getConstraintViolations()
                .stream()
                .map(cv -> cv.getMessage())
                .collect(Collectors.joining(", "));
        return new ErrorDTO(message);
    }
}
