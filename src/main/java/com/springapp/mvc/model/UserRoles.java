package com.springapp.mvc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by kot on 31.08.17.
 */
@Entity
@Table(name = "user_role")
public class UserRoles {

    @Id
    @Column(name = "user_id")
    private long user_id;

    @Column(name = "role_id")
    private long role_id;


    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public void setRole_id(long role_id) {
        this.role_id = role_id;
    }

    public UserRoles() {
    }

}
