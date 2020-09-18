package com.warehouse.demo.model;


import lombok.*;

import javax.persistence.*;

@Entity(name = "bank_account")
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account_balance")
    private Integer accountBallance;
}
