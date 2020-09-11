package com.warehouse.demo.model.request;

import com.warehouse.demo.model.TypeProduct;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {
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

    @NotNull
    private TypeProduct typeOfProduct;

    @NotNull
    private ProducerRequest producer;

    @NotNull
    private WarehouseRequest warehouse;
}
