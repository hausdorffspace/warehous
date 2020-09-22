package com.warehouse.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.ZonedDateTime;
import java.util.Optional;

@RestControllerAdvice
public class EmptyDatabasePianoExceptionAdvice {


    @ExceptionHandler(EmptyDatabasePianoException.class)
    public ResponseEntity<ResponseBodyException> emptyDatabasePianoExceptionandler(EmptyDatabasePianoException e) {
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(new ResponseBodyException(
                Optional.ofNullable(e.getMessage()).orElse("No piano in warehouse"),
                e,
                httpStatus,
                ZonedDateTime.now()
        ), httpStatus);
    }

}
