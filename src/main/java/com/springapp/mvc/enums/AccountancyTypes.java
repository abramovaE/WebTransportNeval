package com.springapp.mvc.enums;

/**
 * Created by oem on 15.08.18.
 */
public enum AccountancyTypes {
//    зональная,
    CHECK("чековая"),
    ODOM("одометрическая");

    private String name;

    AccountancyTypes(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }
}
