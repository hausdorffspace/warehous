package com.warehouse.demo.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Worker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "worker_id")
    private Long id;

    @ManyToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    @JoinColumn(name = "warehouse_id")
    private Warehouse warehouse;

    private String name;

    private String surname;

    @Column(name = "phone_number")
    private Long phoneNumber;

    private Long pesel;

    private Specialization specialization;
}
