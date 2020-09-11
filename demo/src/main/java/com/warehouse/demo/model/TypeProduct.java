package com.warehouse.demo.model;



//TODO tv, pc, ultrabook, washmachine etc.
//TODO edite all enum , make a new fild with specification etc.
public enum TypeProduct {
    INDUSTRIAL("description for industrial type product"),
    COMESTIBLE("description for comestible type product");

    private String description;

    TypeProduct(String description) {
        this.description = description;
    }
}
