package com.springapp.mvc.model;

import java.time.Month;
import java.time.Year;

/**
 * Created by oem on 23.02.19.
 */
public class Expense {

    private AdressesCoordinates address;
    private User user;
    private int startDay,endDay;
    private int startYear, endYear;
    private String startMonth, endMonth;

    public Expense() {
    }

    public AdressesCoordinates getAddress() {
        return address;
    }

    public void setAddress(AdressesCoordinates address) {
        this.address = address;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public int getStartYear() {
        return startYear;
    }

    public void setStartYear(int startYear) {
        this.startYear = startYear;
    }

    public int getEndYear() {
        return endYear;
    }

    public void setEndYear(int endYear) {
        this.endYear = endYear;
    }


    public String getEndMonth() {
        return endMonth;
    }

    public void setEndMonth(String endMonth) {
        this.endMonth = endMonth;
    }

    public String getStartMonth() {

        return startMonth;
    }

    public void setStartMonth(String startMonth) {
        this.startMonth = startMonth;
    }

    public int getStartDay() {
        return startDay;
    }

    public void setStartDay(int startDay) {
        this.startDay = startDay;
    }

    public int getEndDay() {
        return endDay;
    }

    public void setEndDay(int endDay) {
        this.endDay = endDay;
    }


    @Override
    public String toString() {
        return "Expense{" +
                "address=" + address +
                ", user=" + user +
                ", startDay=" + startDay +
                ", endDay=" + endDay +
                ", startYear=" + startYear +
                ", endYear=" + endYear +
                ", startMonth='" + startMonth + '\'' +
                ", endMonth='" + endMonth + '\'' +
                '}';
    }
}
