package com.warehouse.demo.service;


import com.warehouse.demo.model.*;
import com.warehouse.demo.model.request.PianoRequest;
import com.warehouse.demo.repository.PianoRepository;
import com.warehouse.demo.utility.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;


@Service
public class PianoService {

    private PianoRepository pianoRepository;

    private Mapper mapper;

    @Autowired
    public PianoService(PianoRepository pianoRepository, Mapper mapper) {
        this.pianoRepository = pianoRepository;
        this.mapper = mapper;
    }

    public Optional<Piano> save(PianoRequest pianoRequest) {
        return Optional.ofNullable(pianoRepository.save(mapPianoRequestToPiano(pianoRequest)));
    }

    public Optional<Piano> getPianioByName(String name) {
        return Optional.ofNullable(pianoRepository.getPianoByName(name));
    }

    public Optional<List<Piano>> getAllPianoByModel(String modelOfPiano) {
        return Optional.ofNullable(pianoRepository.getAllPianoByModel(mapper.mapperModelOfPiano(modelOfPiano)));
    }

    public Optional<List<Piano>> getAllPiano() {
        return Optional.ofNullable(pianoRepository.findAll());
    }

    public Optional<Piano> updatePianoPriceWithSku(String sku, Integer price) {
        Integer isUpdate = pianoRepository.updatePianoPriceWithSku(sku, price);
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
                .modelOfPiano(pianoRequest.getModelOfPiano())
                .producer(Producer.builder()
                        .companyName(pianoRequest.getProducer().getCompanyName())
                        .build())
                .warehouse(Warehouse.builder()
                        .description(pianoRequest.getWarehouse().getDescription())
                        .location(pianoRequest.getWarehouse().getLocation())
                        .build())
                .build();
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
