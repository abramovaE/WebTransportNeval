package com.springapp.mvc.enums;

/**
 * Created by oem on 16.08.18.
 */
public enum TransmissionTypes {


    MECHAN("механика"),
    AUTO("автомат");


    private String name;

    TransmissionTypes(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

