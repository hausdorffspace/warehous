package com.warehouse.demo.model.request;

import com.warehouse.demo.model.ModelOfPiano;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PianoRequest {
    @NotBlank
    private String name;

    @Min(value = 1)
    private Integer weight;

    @NotNull
    private Integer price;

    @NotNull
    private DimensionRequest dimension;

    @NotBlank
    private String sku;

    //expect S, M, O, A, B, C, D
    @NotNull
    private ModelPianoRequest modelOfPiano;

    @NotNull
    private ProducerRequest producer;

    @NotNull
    private WarehouseRequest warehouse;
}
