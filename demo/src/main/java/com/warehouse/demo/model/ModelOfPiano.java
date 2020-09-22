package com.warehouse.demo.model;


import java.io.Serializable;


public enum ModelOfPiano implements Serializable {
    GRAND_PIANO_S_155(""),
    GRAND_PIANO_M_170(""),
    GRAND_PIANO_O_180(""),
    GRAND_PIANO_A_188(""),
    GRAND_PIANO_B_211(""),
    GRAND_PIANO_C_227(""),
    GRAND_PIANO_D_274(""),
    UPRIGHT_PIANO_V_125(""),
    UPRIGHT_PIANO_K_132("");

    private String description;

    ModelOfPiano(String description) {
        this.description = description;
    }
}
