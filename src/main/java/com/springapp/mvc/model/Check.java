package com.springapp.mvc.model;

import javax.persistence.*;

/**
 * Created by oem on 24.11.18.
 */
@Entity
@Table(name = "check")
public class Check extends Model{



    @Column(name = "value")
    private String value;

    public Check() {
        super();
    }


    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
