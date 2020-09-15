package com.warehouse.demo.controller;


import com.warehouse.demo.Test;
import com.warehouse.demo.exception.CannotSavePianoToTheDatabase;
import com.warehouse.demo.exception.EmptyDatabasePianoException;
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
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController(value = "/api")
public class PianoController {

    private PianoService pianoService;

    @Autowired
    public PianoController(PianoService pianoService) {
        this.pianoService = pianoService;
    }


    @PostMapping(value = "/save")
    public ResponseEntity<PianoResponse> savePiano(@Valid @RequestBody PianoRequest pianoRequest) {
        PianoResponse pianoResponse = pianoService.save(pianoRequest).orElseThrow(() -> new CannotSavePianoToTheDatabase(pianoRequest));
        return new ResponseEntity<>(pianoResponse, HttpStatus.CREATED);
    }

    @GetMapping(value = "/piano")
    public ResponseEntity<PianoResponse> getPianoByName(@RequestParam(name = "pianoName") String name) {
        Piano pianoByName = pianoService.getPianioByName(name).orElseThrow(() -> new PianoNotFoundException(name));
        return new ResponseEntity<>(mapPianoToResponsePiano(pianoByName), HttpStatus.OK);
    }

    //TODO should return to client list<responsePiano> !!!!!!!!!!!!!!!!!!!!11
    @GetMapping(value = "/pianos")
    public ResponseEntity<List<PianoResponse>> getAllPiano() {
        return new ResponseEntity<>(
                mapListOfPianoToListOfPianoResponse(pianoService.getAllPiano()).orElseThrow(EmptyDatabasePianoException::new),
                HttpStatus.OK);
    }

    @GetMapping(value = "/pianos")
    public ResponseEntity<List<PianoResponse>> getAllPianoByModel(@PathVariable(name = "model") String model) {
        return new ResponseEntity<>(
                mapListOfPianoToListOfPianoResponse(pianoService.getAllPianoByModel(model)).orElseThrow(() -> new PianoNotFoundException(model)),
                HttpStatus.OK);
    }

    @PutMapping(value = "/updatePiano")
    public ResponseEntity<PianoResponse> updatePianoWithSku(@PathVariable(name = "sku") String sku) {
        return new ResponseEntity<>(
                mapOptionalPianoToOptionalResponsePiano(pianoService.updatePianoWithSku(sku)).orElseThrow(() -> new PianoNotFoundException(sku)),
                HttpStatus.OK);
    }

    @DeleteMapping(value = "/deletePianoWithSku")
    public ResponseEntity<PianoResponse> deletePianoWithSku(@PathVariable(name = "sku") String sku) {
        return new ResponseEntity<>(
                mapOptionalPianoToOptionalResponsePiano(pianoService.deletePianoWithSku(sku)).orElseThrow(() -> new PianoNotFoundException(sku)),
                HttpStatus.OK);
    }


    @GetMapping(value = "/test")
    public ResponseEntity<?> test(@RequestBody Test test){
        return ResponseEntity.ok(test.getTest());

    }

    private Optional<List<PianoResponse>> mapListOfPianoToListOfPianoResponse(Optional<List<Piano>> optionalPianos) {
        return optionalPianos.map(pianos ->
                pianos.stream()
                        .map(this::mapPianoToResponsePiano)
                        .collect(Collectors.toList())
        );
    }

    //duplikacja
    private PianoResponse mapPianoToResponsePiano(Piano piano) {
        return PianoResponse.builder()
                .id(piano.getId())
                .name(piano.getName())
                .price(piano.getPrice())
                .weight(piano.getWeight())
                .SKU(piano.getSKU())
                .dimension(DimensionResponse.builder()
                        .height(piano.getDimension().getHeight())
                        .width(piano.getDimension().getWidth())
                        .Length(piano.getDimension().getLength())
                        .build())
                .modelOfPiano(piano.getModelOfPiano())
                .producer(ProducerResponse.builder()
                        .companyName(piano.getProducer().getCompanyName())
                        .build())
                .warehouse(WarehouseResponse.builder()
                        .description(piano.getWarehouse().getDescription())
                        .location(piano.getWarehouse().getLocation())
                        .build())
                .build();
    }

    private Optional<PianoResponse> mapOptionalPianoToOptionalResponsePiano(Optional<Piano> optionalPiano) {
        return optionalPiano.map(piano -> PianoResponse.builder()
                .id(piano.getId())
                .name(piano.getName())
                .price(piano.getPrice())
                .weight(piano.getWeight())
                .SKU(piano.getSKU())
                .dimension(DimensionResponse.builder()
                        .height(piano.getDimension().getHeight())
                        .width(piano.getDimension().getWidth())
                        .Length(piano.getDimension().getLength())
                        .build())
                .modelOfPiano(piano.getModelOfPiano())
                .producer(ProducerResponse.builder()
                        .companyName(piano.getProducer().getCompanyName())
                        .build())
                .warehouse(WarehouseResponse.builder()
                        .description(piano.getWarehouse().getDescription())
                        .location(piano.getWarehouse().getLocation())
                        .build())
                .build());
    }

}
