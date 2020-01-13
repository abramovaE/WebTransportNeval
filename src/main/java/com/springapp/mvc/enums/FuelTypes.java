package com.springapp.mvc.enums;

/**
 * Created by oem on 16.08.18.
 */
public enum  FuelTypes {
    NULL(null),
    BENZ("бензин"),
    DIESEL("дизель");


    private String name;

    FuelTypes(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
