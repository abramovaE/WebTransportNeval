package com.springapp.mvc.model;

public class StatisticUser {


    private  User user;
    private double company;
    private double totalAmort;
    private int workingDays;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public double getCompany() {
        return company;
    }

    public void setCompany(double company) {
        this.company = company;
    }

    public double getTotalAmort() {
        return totalAmort;
    }

    public void setTotalAmort(double totalAmort) {
        this.totalAmort = totalAmort;
    }

    public int getWorkingDays() {
        return workingDays;
    }

    public void setWorkingDays(int workingDays) {
        this.workingDays = workingDays;
    }
}
