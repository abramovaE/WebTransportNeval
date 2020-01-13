package com.springapp.mvc.model;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by oem on 03.04.18.
 */
public class UserGraphics {
    private List<Report> listOfUserReports = new LinkedList<>();
    private List<Double> listOfTotalSumm = new LinkedList<>();
    private List<Double> listOfCompany = new LinkedList<>();

    public List<Report> getListOfUserReports() {
        return listOfUserReports;
    }

    public void setListOfUserReports(List<Report> listOfUserReports) {
        this.listOfUserReports = listOfUserReports;
    }

    public List<Double> getListOfTotalSumm() {
        return listOfTotalSumm;
    }

    public void setListOfTotalSumm(List<Double> listOfTotalSumm) {
        this.listOfTotalSumm = listOfTotalSumm;
    }

    public List<Double> getListOfCompany() {
        return listOfCompany;
    }

    public void setListOfCompany(List<Double> listOfCompany) {
        this.listOfCompany = listOfCompany;
    }
}
