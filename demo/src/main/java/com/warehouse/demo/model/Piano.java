package com.warehouse.demo.model;


import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Piano {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    private String name;

    private Integer weight;

    private Integer price;

    @OneToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER  //to always fetch the data
    )
    @JoinColumn(name = "dimension_id")
    private Dimension dimension;

    private String SKU;

    @Enumerated(EnumType.STRING)
    @Column(name = "model_of_piano")
    private ModelOfPiano modelOfPiano;

    @ManyToOne(
            cascade = CascadeType.ALL
    )
    @JoinColumn(name = "producer_id")
    private Producer producer;

    @ManyToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "warehouse_id")
    private Warehouse warehouse;
}
