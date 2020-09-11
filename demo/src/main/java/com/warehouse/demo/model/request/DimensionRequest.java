package com.warehouse.demo.model.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DimensionRequest {

    private Integer height;

    private Integer width;

    private Integer length;
}
