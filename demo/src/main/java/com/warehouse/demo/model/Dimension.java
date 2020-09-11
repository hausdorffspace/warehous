package com.warehouse.demo.model.status;

import com.warehouse.demo.model.Product;
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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "priduct_id")
    private Product product;
}
