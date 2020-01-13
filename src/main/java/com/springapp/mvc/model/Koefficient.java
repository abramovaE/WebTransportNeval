package com.springapp.mvc.model;

/**
 * Created by oem on 30.03.18.
 */
public class Koefficient {
    private String name;
    private int value;
    private String primechanie;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getPrimechanie() {
        return primechanie;
    }

    public void setPrimechanie(String primechanie) {
        this.primechanie = primechanie;
    }

    public Koefficient() {
    }

    public Koefficient(String name, int value, String primechanie) {
        this.name = name;
        this.value = value;
        this.primechanie = primechanie;
    }
}
