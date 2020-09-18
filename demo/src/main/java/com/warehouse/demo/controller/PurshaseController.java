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

import java.util.Optional;

@RestController
public class PurshaseController {

    @Autowired
    private PurshaseService purshaseService;

    @PutMapping(value = "/sellPiano")
    public ResponseEntity<PianoResponse> sellPiano(
            @PathVariable(name = "sku") String sku) {
        System.out.println(sku);
        return new ResponseEntity<>(purshaseService.sellPiano(sku)
                .orElseThrow(() -> new PianoNotFoundException(sku)),
                HttpStatus.OK);
    }




}
