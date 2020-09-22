package com.warehouse.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.ZonedDateTime;

@RestControllerAdvice
public class PianoNotFoundAdvice {

    @ExceptionHandler(PianoNotFoundException.class)
    public ResponseEntity<?> pianoNotFoundHandler(PianoNotFoundException ex){
        HttpStatus notFound = HttpStatus.NOT_FOUND;
        ResponseBodyException exceptionBody = new ResponseBodyException(
                ex.getMessage(),
                ex,
                notFound,
                ZonedDateTime.now()
        );
        return new ResponseEntity<>(exceptionBody,notFound);
    }

}
