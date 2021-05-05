package com.jparandag.microservices.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Data
public class ExceptionResponse {

    private Date timestamp;
    private String errorCode;
    private String message;
    private List<String> errors;

    public ExceptionResponse(Date timestamp, String errorCode, String message, List<String> errors) {
        super();
        this.timestamp = timestamp;
        this.errorCode = errorCode;
        this.message = message;
        this.errors = errors;
    }

    public ExceptionResponse(Date timestamp, String errorCode, String message, String error) {
        super();
        this.timestamp = timestamp;
        this.errorCode = errorCode;
        this.message = message;
        errors = Arrays.asList(error);
    }
}
