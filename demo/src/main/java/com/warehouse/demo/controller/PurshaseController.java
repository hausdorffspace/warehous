package com.warehouse.demo.controller;


import com.warehouse.demo.exception.PianoNotFoundException;
import com.warehouse.demo.model.Piano;
import com.warehouse.demo.model.response.PianoResponse;
import com.warehouse.demo.service.PurshaseService;
import com.warehouse.demo.utility.PianoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Email;
import java.time.Period;
import java.util.Optional;


@RestController
public class PurshaseController {

    private PurshaseService purshaseService;

    private PianoMapper pianoMapper;

    @Autowired
    public PurshaseController(PurshaseService purshaseService, PianoMapper pianoMapper) {
        this.purshaseService = purshaseService;
        this.pianoMapper = pianoMapper;
    }

    @PutMapping(value = "/sellPiano/{sku}")
    public ResponseEntity<Piano> sellPiano(
            @PathVariable(name = "sku") String sku) {
        return new ResponseEntity<>(purshaseService.sellPiano(sku)
                .orElseThrow(() -> new PianoNotFoundException(sku)),
                HttpStatus.OK);
    }


    @PutMapping(value = "/borrowPiano/{sku}/{period}")
    public ResponseEntity<PianoResponse> borrowPiano(
            @PathVariable(name = "sku") String sku,
            @PathVariable(name = "period") Long period,
            @PathVariable(name = "addressEmial") String addressEmial
    ) {
        return new ResponseEntity<>(
                pianoMapper.mapOptionalPianoToOptionalResponsePiano(purshaseService.borrowPianoWithSKUForThePeriod(sku, period, addressEmial))
                        .orElseThrow(() -> new PianoNotFoundException(sku)),
                HttpStatus.OK);
    }


}
