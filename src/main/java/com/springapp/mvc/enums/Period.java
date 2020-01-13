package com.springapp.mvc.enums;

/**
 * Created by oem on 24.10.18.
 */
public enum Period {

    SUMMER("Лето"),
    WINTER("Зима");

    String name;

    Period(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


}
