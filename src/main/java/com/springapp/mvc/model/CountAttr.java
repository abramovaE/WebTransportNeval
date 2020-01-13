package com.springapp.mvc.model;

import javax.persistence.Entity;

@Entity
public class CountAttr{
    private int monthNumber;
    private int year;
    private long userId;


    public CountAttr() {
    }

    public int getMonthNumber() {
        return monthNumber;
    }

    public void setMonthNumber(int monthNumber) {
        this.monthNumber = monthNumber;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }


    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "CountAttr{" +
                "monthNumber=" + monthNumber +
                ", year=" + year +
                ", user=" + userId +
                '}';
    }
}