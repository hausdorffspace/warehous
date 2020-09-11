package com.warehouse.demo.model.response;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DimensionResponse {

    private Integer width;

    private Integer height;

    private Integer Length;
}
