package com.warehouse.demo.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.ZonedDateTime;

@RestControllerAdvice
public class CannotSavePIanoToTheDatabaseAdvice {

    @ExceptionHandler(CannotSavePianoToTheDatabase.class)
    public ResponseEntity<?> canNotSaavePianoToTheDatabaseHandler(CannotSavePianoToTheDatabase ex) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(new ResponseBodyException(
                ex.getMessage(),
                ex,
                badRequest,
                ZonedDateTime.now()
        ), badRequest);
    }
}
