package com.warehouse.demo.model;


import java.io.Serializable;


public enum ModelOfPiano implements Serializable {
    GRAND_PIANO_S_155(""),
    GRAND_PIANO_M_170("dawd"),
    GRAND_PIANO_O_180("dawd"),
    GRAND_PIANO_A_188("dawd"),
    GRAND_PIANO_B_211("dawd"),
    GRAND_PIANO_C_227("dawd"),
    GRAND_PIANO_D_274("dawd"),
    UPRIGHT_PIANO_V_125(""),
    UPRIGHT_PIANO_K_132("");

    private String description;

    ModelOfPiano(String description) {
        this.description = description;
    }
}
