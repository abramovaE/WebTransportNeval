package com.springapp.mvc.model;



import java.util.List;

public class Statistic {

//    private int startDay, endDay;
    private int startYear, endYear;
    private String startMonth, endMonth;




    public Statistic() {
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

//    public int getStartDay() {
//        return startDay;
//    }
//
//    public void setStartDay(int startDay) {
//        this.startDay = startDay;
//    }
//
//    public int getEndDay() {
//        return endDay;
//    }
//
//    public void setEndDay(int endDay) {
//        this.endDay = endDay;
//    }





    @Override
    public String toString() {
        return "Statistic{" +
//                "startDay=" + startDay +
//                ", endDay=" + endDay +
                ", startYear=" + startYear +
                ", endYear=" + endYear +
                ", startMonth='" + startMonth + '\'' +
                ", endMonth='" + endMonth + '\'' +
                ", usersId=" + usersId +
                '}';
    }

    private List<Integer> usersId;


    public List<Integer> getUsersId() {
        return usersId;
    }

    public void setUsersId(List<Integer> usersId) {
        this.usersId = usersId;
    }



}