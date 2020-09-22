package com.warehouse.demo.exception;

import com.warehouse.demo.model.request.PianoRequest;

public class CannotSavePianoToTheDatabase extends RuntimeException {

    //TODO
    public CannotSavePianoToTheDatabase(PianoRequest pianoRequest) {
        super(" " + pianoRequest);
    }
}
