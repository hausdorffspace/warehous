package com.warehouse.demo.controller;


import com.warehouse.demo.exception.CannotSavePianoToTheDatabase;
import com.warehouse.demo.exception.PianoNotFoundException;
import com.warehouse.demo.model.Piano;
import com.warehouse.demo.model.request.PianoRequest;
import com.warehouse.demo.model.response.DimensionResponse;
import com.warehouse.demo.model.response.PianoResponse;
import com.warehouse.demo.model.response.ProducerResponse;
import com.warehouse.demo.model.response.WarehouseResponse;
import com.warehouse.demo.service.PianoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController(value = "/api")
public class PianoController {

    private PianoService pianoService;

    @Autowired
    public PianoController(PianoService pianoService) {
        this.pianoService = pianoService;
    }

    // exception handler
    @PostMapping(value = "/save")
    public ResponseEntity<PianoResponse> savePiano(@Valid @RequestBody PianoRequest pianoRequest) {
        PianoResponse pianoResponse = pianoService.save(pianoRequest).orElseThrow(() -> new CannotSavePianoToTheDatabase(pianoRequest));
        return new ResponseEntity<>(pianoResponse, HttpStatus.CREATED);
    }

    //TODO Not working
    @GetMapping(value = "/piano")
    public ResponseEntity<PianoResponse> getPianoByName(@RequestParam(name = "pianoName") String name) {
        Piano pianoByName = pianoService.getPianioByName(name).orElseThrow(() -> new PianoNotFoundException(name));
        return new ResponseEntity<>(
                PianoResponse.builder()
                        .id(pianoByName.getId())
                        .name(pianoByName.getName())
                        .price(pianoByName.getPrice())
                        .weight(pianoByName.getWeight())
                        .SKU(pianoByName.getSKU())
                        .dimension(DimensionResponse.builder()
                                .height(pianoByName.getDimension().getHeight())
                                .width(pianoByName.getDimension().getWidth())
                                .Length(pianoByName.getDimension().getLength())
                                .build())
                        .modelOfPiano(pianoByName.getModelOfPiano())
                        .producer(ProducerResponse.builder()
                                .companyName(pianoByName.getProducer().getCompanyName())
                                .build())
                        .warehouse(WarehouseResponse.builder()
                                .description(pianoByName.getWarehouse().getDescription())
                                .location(pianoByName.getWarehouse().getLocation())
                                .build())
                        .build(),
                HttpStatus.OK);
    }

}
