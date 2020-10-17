package com.warehouse.demo.utility;

import com.warehouse.demo.model.Piano;
import com.warehouse.demo.model.response.DimensionResponse;
import com.warehouse.demo.model.response.PianoResponse;
import com.warehouse.demo.model.response.ProducerResponse;
import com.warehouse.demo.model.response.WarehouseResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Component
public class PianoMapper {

    public Optional<List<PianoResponse>> mapListOfPianoToListOfPianoResponse(Optional<List<Piano>> optionalPianos) {
        return optionalPianos.map(pianos ->
                pianos.stream()
                        .map(this::mapPianoToResponsePiano)
                        .collect(Collectors.toList())
        );
    }

    public PianoResponse mapPianoToResponsePiano(Piano piano) {
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

    public Optional<PianoResponse> mapOptionalPianoToOptionalResponsePiano(Optional<Piano> optionalPiano) {
        return optionalPiano.map(this::mapPianoToResponsePiano);
    }
}
