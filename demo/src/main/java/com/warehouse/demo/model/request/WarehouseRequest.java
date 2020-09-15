package com.warehouse.demo.model.request;


import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WarehouseRequest {

    private String description;

    private String location;
}
