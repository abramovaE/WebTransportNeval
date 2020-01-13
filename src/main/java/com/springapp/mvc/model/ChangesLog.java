package com.springapp.mvc.model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

/**
 * Created by oem on 22.05.18.
 */

@Entity
@Table(name="changesLog")
public class ChangesLog extends Model{



    @Column(name = "subject")
    private String subject;

    @Column(name = "oldData")
    private String oldData;

    @Column(name = "newData")
    private String newData;

    @ManyToOne(optional = true)
    @JoinColumn(name = "whochangedId")
    @LazyCollection(LazyCollectionOption.FALSE)
    private User whoChanged;

    @Column(name = "date")
    private String date;


    @ManyToOne(optional = true)
    @JoinColumn(name = "user_id")
    @LazyCollection(LazyCollectionOption.FALSE)
    private User user;


    @ManyToOne(optional = true)
    @JoinColumn(name = "auto_id")
    @LazyCollection(LazyCollectionOption.FALSE)
    private Auto auto;


    public ChangesLog() {
        super();
    }



    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getOldData() {
        return oldData;
    }

    public void setOldData(String oldData) {
        this.oldData = oldData;
    }

    public String getNewData() {
        return newData;
    }

    public void setNewData(String newData) {
        this.newData = newData;
    }

    public User getWhoChanged() {
        return whoChanged;
    }

    public void setWhoChanged(User whoChanged) {
        this.whoChanged = whoChanged;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Auto getAuto() {
        return auto;
    }

    public void setAuto(Auto auto) {
        this.auto = auto;
    }

    @Override
    public String toString() {
        return "ChangesLog{" +
                "id=" + super.getId() +
                ", subject='" + subject + '\'' +
                ", oldData='" + oldData + '\'' +
                ", newData='" + newData + '\'' +
                ", date='" + date + '\'' +
                ", user=" + user +
                '}';
    }
}
