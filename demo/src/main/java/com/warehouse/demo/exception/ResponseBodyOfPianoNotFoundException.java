package com.warehouse.demo.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;


@AllArgsConstructor
@Setter
@Getter
public class ResponseBodyOfPianoNotFoundException {

    private final String message;

    private final Throwable throwable;

    private final HttpStatus httpStatus;

    private final ZonedDateTime timestamp;

}
