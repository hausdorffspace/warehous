package com.warehouse.demo.exception;

public class PianoNotFoundException extends RuntimeException {
    public PianoNotFoundException(String message) {
        super("could not find piano: " + message);
    }
}
