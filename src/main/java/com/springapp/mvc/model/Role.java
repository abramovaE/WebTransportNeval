package com.springapp.mvc.model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by kot on 04.08.17.
 */

@Entity
@Table(name = "role")
public class Role extends Model {

    @Column(name = "rolename")
    private String rolename;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Role() {
        super();
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + super.getId() +
                ", rolename='" + rolename + '\'' +

                '}';
    }


}
