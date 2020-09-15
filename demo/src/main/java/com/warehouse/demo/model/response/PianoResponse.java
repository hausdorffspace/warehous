package com.warehouse.demo.model.response;

import com.warehouse.demo.model.ModelOfPiano;
import com.warehouse.demo.model.Producer;
import com.warehouse.demo.model.Warehouse;
import com.warehouse.demo.model.request.DimensionRequest;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class PianoResponse {

    private Long id;

    private String name;

    private Integer weight;

    private Integer price;

    private DimensionResponse dimension;

    private String SKU;

    private ModelOfPiano modelOfPiano;

    private ProducerResponse producer;

    private WarehouseResponse warehouse;

    private Boolean borrowed;
}
