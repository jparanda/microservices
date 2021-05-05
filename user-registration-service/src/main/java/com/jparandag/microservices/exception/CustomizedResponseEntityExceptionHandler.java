package com.jparandag.microservices.exception;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    private static final String INTERNAL_SERVER_ERROR_CODE = "999";
    private static final String INTERNAL_SERVER_ERROR_MSG = "Internal Error, please contact team owner User Management Service";
    private static final String USER_EMAIL_UNIQUE_CODE = "user_001";
    private static final String USER_EMAIL_UNIQUE_MSG = "The email already exists";
    private static final String USER_NOT_FOUND_EXCEPTION_CODE = "user_002";
    private static final String USER_NOT_FOUND_EXCEPTION_MSG = "The user doesn't exist";
    private static final String REQUEST_SERVICE_VALIDATION_CODE = "user_003";
    private static final String REQUEST_SERVICE_VALIDATION_MSG = "Validation failed";

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                new Date(),
                INTERNAL_SERVER_ERROR_CODE,
                INTERNAL_SERVER_ERROR_MSG,
                request.getDescription(false));
        return new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public final ResponseEntity<Object> handledConstraintsException(Exception ex, WebRequest request) {
        String constrainName = ((ConstraintViolationException)ex.getCause()).getConstraintName();
        String errorMessage = "Error in sql statement " + constrainName;
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                new Date(),
                USER_EMAIL_UNIQUE_CODE,
                USER_EMAIL_UNIQUE_MSG,
                errorMessage);
        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                new Date(),
                USER_NOT_FOUND_EXCEPTION_CODE,
                USER_NOT_FOUND_EXCEPTION_MSG,
                request.getDescription(false));
        return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status,
                                                                  WebRequest request) {
        List<String> errors = new ArrayList<>();
        for (FieldError error: ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }

        ExceptionResponse exceptionResponse = new ExceptionResponse(
                new Date(),
                REQUEST_SERVICE_VALIDATION_CODE,
                REQUEST_SERVICE_VALIDATION_MSG,
                errors);
        return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

}
