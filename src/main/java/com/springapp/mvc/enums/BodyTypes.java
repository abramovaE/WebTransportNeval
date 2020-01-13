package com.springapp.mvc.enums;

/**
 * Created by oem on 15.08.18.
 */
public enum BodyTypes {
    NULL(null),
    JEEP("внедорожник"),
    COUPE("купе"),
    SEDAN("седан"),
    UNIVERSAL("универсал"),
    HATCHBACH("хэтчбек"),
    MINIBUS("микроавтобус");

    private String name;

    BodyTypes(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }
}
