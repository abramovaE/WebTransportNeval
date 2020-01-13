package com.springapp.mvc.enums;

/**
 * Created by oem on 15.08.18.
 */
public enum ClimateMachineTypes {

    NULL(null),
    CLIMATE("климат-контроль"),
    CONDITIONER("кондиционер"),
    NONE("нет");


    private String name;


    ClimateMachineTypes(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
