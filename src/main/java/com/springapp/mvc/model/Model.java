package com.springapp.mvc.model;


import javax.persistence.*;

@MappedSuperclass
public abstract class Model {


    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;


    public Model() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


}
