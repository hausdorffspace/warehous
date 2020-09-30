package com.warehouse.demo.utility;

import com.warehouse.demo.model.ModelOfPiano;
import com.warehouse.demo.model.Piano;
import com.warehouse.demo.model.request.ModelPianoRequest;
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

    public String mapperModelOfPiano(String modelOfPiano) {
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
}
