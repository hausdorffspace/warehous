package com.warehouse.demo.utility;

import com.warehouse.demo.model.Piano;
import com.warehouse.demo.model.response.DimensionResponse;
import com.warehouse.demo.model.response.PianoResponse;
import com.warehouse.demo.model.response.ProducerResponse;
import com.warehouse.demo.model.response.WarehouseResponse;
import org.springframework.stereotype.Component;

@Component
public class Mapper {

    private Mapper() {
    }

    public static PianoResponse mapPianoToPianoResponse(Piano piano){
        return PianoResponse.builder()
                .id(piano.getId())
                .SKU(piano.getSKU())
                .price(piano.getPrice())
                .borrowed(piano.getBorrowed())
                .weight(piano.getWeight())
                .name(piano.getName())
                .warehouse(WarehouseResponse.builder()
                        .location(piano.getWarehouse().getLocation())
                        .description(piano.getWarehouse().getDescription())
                        .build())
                .producer(ProducerResponse.builder()
                        .companyName(piano.getProducer().getCompanyName())
                        .build())
                .modelOfPiano(piano.getModelOfPiano())
                .dimension(DimensionResponse.builder()
                        .height(piano.getDimension().getHeight())
                        .Length(piano.getDimension().getLength())
                        .width(piano.getDimension().getWidth())
                        .build())
                .build();
    }
}
