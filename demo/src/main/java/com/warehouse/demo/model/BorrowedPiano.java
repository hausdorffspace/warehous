package com.warehouse.demo.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Period;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class BorrowedPiano implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "piano_id")
    private Piano borrowedPiano;

    private Double priceForTheEntireRentalPeriod;

    private Double priceForOneMonth;

    private Long totalTimeForRentalPeriod;

    private Customer customer;
}
