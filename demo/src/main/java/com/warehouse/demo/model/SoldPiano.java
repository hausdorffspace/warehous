package com.warehouse.demo.model;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SoldPiano implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer weight;

    private Integer price;

    private String SKU;

    private ModelOfPiano modelOfPiano;

    private Producer producer;

    private Warehouse warehouse;

    @OneToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "customer_id")
    private Customer buyer;

}