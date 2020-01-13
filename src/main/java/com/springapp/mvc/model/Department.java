package com.springapp.mvc.model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

/**
 * Created by oem on 23.05.19.
 */

@Entity
@Table(name = "department")
public class Department extends Model{



    @Column(name = "name")
    private String name;

    @Column(name = "prim")
    private String prim;


    @Column(name = "link")
    private String link;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<User> users;

    public Department() {
        super();
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrim() {
        return prim;
    }

    public void setPrim(String prim) {
        this.prim = prim;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + super.getId() +
                ", name='" + name + '\'' +
                ", prim='" + prim + '\'' +
                ", users=" + users +
                '}';
    }

    @Transient
    private List<Integer> usersId;


    public List<Integer> getUsersId() {
        return usersId;
    }

    public void setUsersId(List<Integer> usersId) {
        this.usersId = usersId;
    }


}
