package com.warehouse.demo.controller;


import com.warehouse.demo.exception.PianoNotFoundException;
import com.warehouse.demo.model.request.PianoRequest;
import com.warehouse.demo.model.response.PianoResponse;
import com.warehouse.demo.service.PianoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class PianoController {

    private PianoService pianoService;

    @Autowired
    public PianoController(PianoService pianoService) {
        this.pianoService = pianoService;
    }

    // exception handler
    @PostMapping(value = "/save")
    public ResponseEntity<PianoResponse> saveProduct(@Valid @RequestBody PianoRequest pianoRequest) {
        return new ResponseEntity<>(pianoService.save(pianoRequest), HttpStatus.CREATED);
    }

    @GetMapping(value = "/product")
    public ResponseEntity<PianoResponse> getProductByName(@RequestBody String name) {
        return new ResponseEntity<>(
                pianoService.getPianioByName(name).orElseThrow(() -> new PianoNotFoundException(name)),
                HttpStatus.OK);
    }

}
