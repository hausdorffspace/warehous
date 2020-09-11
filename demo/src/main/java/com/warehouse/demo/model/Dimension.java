package com.warehouse.demo.model;

import lombok.*;

import javax.persistence.*;


@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Dimension {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dimension_id")
    private Long idDimension;

    private Integer height;

    private Integer width;

    private Integer length;
}
