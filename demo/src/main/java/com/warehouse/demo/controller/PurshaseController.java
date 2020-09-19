package com.warehouse.demo.controller;


import com.warehouse.demo.exception.PianoNotFoundException;
import com.warehouse.demo.model.response.PianoResponse;
import com.warehouse.demo.service.PurshaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class PurshaseController {

    private PurshaseService purshaseService;

    @Autowired
    public PurshaseController(PurshaseService purshaseService) {
        this.purshaseService = purshaseService;
    }

    @PutMapping(value = "/sellPiano/{sku}")
    public ResponseEntity<PianoResponse> sellPiano(
            @PathVariable(name = "sku") String sku) {
        return new ResponseEntity<>(purshaseService.sellPiano(sku)
                .orElseThrow(() -> new PianoNotFoundException(sku)),
                HttpStatus.OK);
    }




}
