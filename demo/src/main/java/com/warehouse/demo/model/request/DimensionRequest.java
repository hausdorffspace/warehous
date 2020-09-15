package com.warehouse.demo.model.request;


import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DimensionRequest {

    private Integer height;

    private Integer width;

    private Integer length;
}
