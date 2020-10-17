package com.warehouse.demo.controller;


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
import com.warehouse.demo.utility.PianoMapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController("/api")
public class PianoController {

    private PianoService pianoService;

    private PianoMapper pianoMapper;

    @Autowired
    public PianoController(PianoService pianoService, PianoMapper pianoMapper) {
        this.pianoMapper = pianoMapper;
        this.pianoService = pianoService;
    }

    //ok
    @PostMapping(value = "/save")
    public ResponseEntity<PianoResponse> savePiano(@Valid @RequestBody PianoRequest pianoRequest) {
        return new ResponseEntity<>(
                pianoMapper.mapOptionalPianoToOptionalResponsePiano(pianoService.save(pianoRequest)).orElseThrow(()-> new CannotSavePianoToTheDatabase(pianoRequest)),
                HttpStatus.CREATED);
    }

    @GetMapping(value = "/piano/{name}")
    public ResponseEntity<PianoResponse> getPianoByName(@PathVariable(name = "name") String name) {
        return new ResponseEntity<>(pianoMapper.mapPianoToResponsePiano(pianoService.getPianioByName(name).orElseThrow(() -> new PianoNotFoundException(name))), HttpStatus.OK);
    }

    //ok
    @ApiOperation(value = "return all piano")
    @GetMapping(value = "/pianos")
    public ResponseEntity<List<PianoResponse>> getAllPiano() {
        return new ResponseEntity<>(
                pianoMapper.mapListOfPianoToListOfPianoResponse(pianoService.getAllPiano()).orElseThrow(EmptyDatabasePianoException::new),
                HttpStatus.OK);
    }

    //ok
    @ApiOperation(value = "find piano by model" ,notes = "required param is: \n" +
            "S for GRAND_PIANO_S_155,\n" +
            "M for    GRAND_PIANO_M_170,\n" +
            "O for    GRAND_PIANO_O_180,\n" +
            "A for    GRAND_PIANO_A_188,\n" +
            "B for    GRAND_PIANO_B_211,\n" +
            "C for    GRAND_PIANO_C_227,\n" +
            "D for    GRAND_PIANO_D_274,\n" +
            "V for    UPRIGHT_PIANO_V_125,\n" +
            "K for    UPRIGHT_PIANO_K_132")
    @GetMapping(value = "/pianosByModel/{model}")
    public ResponseEntity<List<PianoResponse>> getAllPianoByModel(@PathVariable(name = "model") String model) {
        return new ResponseEntity<>(
                pianoMapper.mapListOfPianoToListOfPianoResponse(pianoService.getAllPianoByModel(model)).orElseThrow(() -> new PianoNotFoundException(model)),
                HttpStatus.OK);
    }

    //ok
    @PutMapping(value = "/updatePiano/{sku}/{price}")
    public ResponseEntity<PianoResponse> updatePianoWithSku(
            @PathVariable(name = "sku") String sku,
            @PathVariable(name = "price") Integer price
    ) {
        return new ResponseEntity<>(
                pianoMapper.mapOptionalPianoToOptionalResponsePiano(pianoService.updatePianoPriceWithSku(sku, price))
                        .orElseThrow(() -> new PianoNotFoundException(sku)),
                HttpStatus.OK);
    }

    //ok
    @DeleteMapping(value = "deletePianoById/{id}")
    public ResponseEntity<PianoResponse> deletePianoById(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(
                pianoMapper.mapOptionalPianoToOptionalResponsePiano(pianoService.deletePianoById(id))
                        .orElseThrow(() -> new PianoNotFoundException("piano id: " + id)),
                HttpStatus.OK);
    }

    //ok
    @DeleteMapping(value = "/deletePianoWithSku/{sku}")
    public ResponseEntity<PianoResponse> deletePianoWithSku(@PathVariable(name = "sku") String sku) {
        return new ResponseEntity<>(
                pianoMapper.mapOptionalPianoToOptionalResponsePiano(pianoService.deletePianoWithSku(sku)).orElseThrow(() -> new PianoNotFoundException(sku)),
                HttpStatus.OK);
    }

}
