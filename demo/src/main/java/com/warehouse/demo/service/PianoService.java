package com.warehouse.demo.service;


import com.warehouse.demo.model.*;
import com.warehouse.demo.model.request.ModelPianoRequest;
import com.warehouse.demo.model.request.PianoRequest;
import com.warehouse.demo.model.response.DimensionResponse;
import com.warehouse.demo.model.response.PianoResponse;
import com.warehouse.demo.model.response.ProducerResponse;
import com.warehouse.demo.model.response.WarehouseResponse;
import com.warehouse.demo.repository.PianoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;


@Service
public class PianoService {

    @Autowired
    private PianoRepository pianoRepository;

    public Optional<PianoResponse> save(PianoRequest pianoRequest) {

        Piano savePiano = pianoRepository.save(mapPianoRequestToPiano(pianoRequest));

        return Optional.ofNullable(PianoResponse.builder()
                .id(savePiano.getId())
                .name(savePiano.getName())
                .price(savePiano.getPrice())
                .weight(savePiano.getWeight())
                .SKU(savePiano.getSKU())
                .borrowed(savePiano.getBorrowed())
                .dimension(DimensionResponse.builder()
                        .height(savePiano.getDimension().getHeight())
                        .width(savePiano.getDimension().getWidth())
                        .Length(savePiano.getDimension().getLength())
                        .build())
                .modelOfPiano(savePiano.getModelOfPiano())
                .producer(ProducerResponse.builder()
                        .companyName(savePiano.getProducer().getCompanyName())
                        .build())
                .warehouse(WarehouseResponse.builder()
                        .description(savePiano.getWarehouse().getDescription())
                        .location(savePiano.getWarehouse().getLocation())
                        .build())
                .build());
    }

    public Optional<Piano> getPianioByName(String name) {
        return Optional.ofNullable(pianoRepository.getPianoByName(name));
    }

    public Optional<List<Piano>> getAllPianoByModel(String modelOfPiano) {
        return Optional.ofNullable(pianoRepository.getAllPianoByModel(mapperModelOfPiano(modelOfPiano)));
    }

    public Optional<List<Piano>> getAllPiano() {
        return Optional.ofNullable(pianoRepository.findAll());
    }

    public Optional<Piano> updatePianoWithSku(String sku, Integer price) {
        Integer isUpdate = pianoRepository.updatePianoWithSku(sku, price);
        if (isUpdate == 1) {
            return Optional.ofNullable(pianoRepository.getPianoBySKU(sku));
        } else {
            return Optional.empty();
        }
    }

    public Optional<Piano> deletePianoWithSku(String sku) {
        Piano pianoBySKU = pianoRepository.getPianoBySKU(sku);
        Integer isDelete = pianoRepository.deletePianoWithSku(sku);
        if (isDelete == 1) {
            return Optional.ofNullable(pianoBySKU);
        } else {
            return Optional.empty();
        }
    }

    public Optional<Piano> deletePianoById(Long id) {
        Piano pianoById = pianoRepository.getPianoById(id);
        Integer isDelete = pianoRepository.deletePianoWithId(id);
        if (isDelete == 1) {
            return Optional.ofNullable(pianoById);
        } else {
            return Optional.empty();
        }
    }

    private ModelOfPiano modelChecker(ModelPianoRequest model) {
        ModelOfPiano modelOfPiano;
        switch (model) {
            case A:
                modelOfPiano = ModelOfPiano.GRAND_PIANO_A_188;
                break;
            case B:
                modelOfPiano = ModelOfPiano.GRAND_PIANO_B_211;
                break;
            case C:
                modelOfPiano = ModelOfPiano.GRAND_PIANO_C_227;
                break;
            case D:
                modelOfPiano = ModelOfPiano.GRAND_PIANO_D_274;
                break;
            case M:
                modelOfPiano = ModelOfPiano.GRAND_PIANO_M_170;
                break;
            case O:
                modelOfPiano = ModelOfPiano.GRAND_PIANO_O_180;
                break;
            case S:
                modelOfPiano = ModelOfPiano.GRAND_PIANO_S_155;
                break;
            case K:
                modelOfPiano = ModelOfPiano.UPRIGHT_PIANO_K_132;
                break;
            case V:
                modelOfPiano = ModelOfPiano.UPRIGHT_PIANO_V_125;
                break;
            default:
                modelOfPiano = ModelOfPiano.GRAND_PIANO_B_211;
                break;
        }
        return modelOfPiano;
    }

    private Piano mapPianoRequestToPiano(PianoRequest pianoRequest) {
        return Piano.builder()
                .name(pianoRequest.getName())
                .price(pianoRequest.getPrice())
                .weight(pianoRequest.getWeight())
                .dimension(Dimension.builder()
                        .height(pianoRequest.getDimension().getHeight())
                        .width(pianoRequest.getDimension().getWidth())
                        .length(pianoRequest.getDimension().getLength())
                        .build())
                .SKU(generateSKU())
                .borrowed(false)
                .modelOfPiano(modelChecker(pianoRequest.getModelOfPiano()))   // TODO message or description , add rescription in swagger
                .producer(Producer.builder()
                        .companyName(pianoRequest.getProducer().getCompanyName())
                        .build())
                .warehouse(Warehouse.builder()
                        .description(pianoRequest.getWarehouse().getDescription())
                        .location(pianoRequest.getWarehouse().getLocation())
                        .build())
                .build();
    }


    private String mapperModelOfPiano(String modelOfPiano) {
        switch (modelOfPiano) {

            case "A":
                modelOfPiano = ModelOfPiano.GRAND_PIANO_A_188.name();
                break;
            case "B":
                modelOfPiano = ModelOfPiano.GRAND_PIANO_B_211.name();
                break;
            case "C":
                modelOfPiano = ModelOfPiano.GRAND_PIANO_C_227.name();
                break;
            case "D":
                modelOfPiano = ModelOfPiano.GRAND_PIANO_D_274.name();
                break;
            case "M":
                modelOfPiano = ModelOfPiano.GRAND_PIANO_M_170.name();
                break;
            case "O":
                modelOfPiano = ModelOfPiano.GRAND_PIANO_O_180.name();
                break;
            case "S":
                modelOfPiano = ModelOfPiano.GRAND_PIANO_S_155.name();
                break;
            case "K":
                modelOfPiano = ModelOfPiano.UPRIGHT_PIANO_K_132.name();
                break;
            case "V":
                modelOfPiano = ModelOfPiano.UPRIGHT_PIANO_V_125.name();
                break;
            default:
                modelOfPiano = ModelOfPiano.GRAND_PIANO_B_211.name();
                break;
        }
        return modelOfPiano;
    }

    private String generateSKU() {
        int leftLimit = 97;
        int rightLimit = 122;
        int targetStringLength = 10;
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString()
                .toUpperCase();
    }

}
