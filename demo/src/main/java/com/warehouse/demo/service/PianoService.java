package com.warehouse.demo.service;


import com.warehouse.demo.model.*;
import com.warehouse.demo.model.request.DimensionRequest;
import com.warehouse.demo.model.request.PianoRequest;
import com.warehouse.demo.model.response.DimensionResponse;
import com.warehouse.demo.model.response.PianoResponse;
import com.warehouse.demo.model.response.ProducerResponse;
import com.warehouse.demo.model.response.WarehouseResponse;
import com.warehouse.demo.repository.PianoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class PianoService {

    private PianoRepository pianoRepository;

    @Autowired
    public PianoService(PianoRepository pianoRepository) {
        this.pianoRepository = pianoRepository;
    }

    //TODO return optional<pianoResponse> ????
    public PianoResponse save(PianoRequest pianoRequest) {

        Piano save = pianoRepository.save(Piano.builder()
                .name(pianoRequest.getName())
                .price(pianoRequest.getPrice())
                .weight(pianoRequest.getWeight())
                .dimension(Dimension.builder()
                        .height(pianoRequest.getDimension().getHeight())
                        .width(pianoRequest.getDimension().getWidth())
                        .length(pianoRequest.getDimension().getLength())
                        .build())
                .SKU(pianoRequest.getSku())
                .modelOfPiano(ModelOfPiano.GRAND_PIANO_B_211)   // TODO message or description
                .producer(Producer.builder()
                        .companyName(pianoRequest.getProducer().getCompanyName())
                        .build())
                .warehouse(Warehouse.builder()
                        .description(pianoRequest.getWarehouse().getDescription())
                        .location(pianoRequest.getWarehouse().getLocation())
                        .build())
                .build());

        return PianoResponse.builder()
                .id(save.getId())
                .name(save.getName())
                .price(save.getPrice())
                .weight(save.getWeight())
                .SKU(save.getSKU())
                .dimension(DimensionResponse.builder()
                        .height(save.getDimension().getHeight())
                        .width(save.getDimension().getWidth())
                        .Length(save.getDimension().getLength())
                        .build())
                .modelOfPiano(save.getModelOfPiano())
                .producer(ProducerResponse.builder()
                        .companyName(save.getProducer().getCompanyName())
                        .build())
                .warehouse(WarehouseResponse.builder()
                        .description(save.getWarehouse().getDescription())
                        .location(save.getWarehouse().getLocation())
                        .build())
                .build();
    }

    public Optional<PianoResponse> getPianioByName(String name){
        Piano pianoByName = pianoRepository.getPianoByName(name);
        System.out.println(pianoByName);
        return Optional.of(PianoResponse.builder()
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
                .build());
    }

    //TODO
    private ModelOfPiano modelChecker(String model){
        if (model.toLowerCase().contentEquals(new StringBuffer("D"))){
            return ModelOfPiano.GRAND_PIANO_D_274;
        } else {
            return ModelOfPiano.GRAND_PIANO_A_188;
        }
    }

}
