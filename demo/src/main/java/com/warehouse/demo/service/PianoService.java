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

@Service
public class PianoService {

    private PianoRepository pianoRepository;

    @Autowired
    public PianoService(PianoRepository pianoRepository) {
        this.pianoRepository = pianoRepository;
    }

    //TODO return optional<pianoResponse> ????
    public Optional<PianoResponse> save(PianoRequest pianoRequest) {

        Piano savePiano = pianoRepository.save(mapPianoRequestToPiano(pianoRequest));

        return Optional.ofNullable(PianoResponse.builder()
                .id(savePiano.getId())
                .name(savePiano.getName())
                .price(savePiano.getPrice())
                .weight(savePiano.getWeight())
                .SKU(savePiano.getSKU())
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

    public Optional<List<Piano>> getAllPianoByType(String modelOfPiano){
        return Optional.ofNullable(pianoRepository.getAllPianoByModel(modelOfPiano));
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
                .SKU(pianoRequest.getSku())
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

    public List<Piano> getAllPiano() {
        return pianoRepository.findAll();
    }
}
