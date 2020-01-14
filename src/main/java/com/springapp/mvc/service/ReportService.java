package com.springapp.mvc.service;


import com.springapp.mvc.model.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by kot on 10.07.17.
 */
public interface ReportService {

    void saveReport(Report report);
    void updateReport(Report report);
    Report findByIdReport(long id);
    void deleteReport(long id);

    List<Report> getAllReports();
    List<Report> getAllReportsByYearByMonth(int year, int month);
    List<Report> getAllReportsByUser(User user);

    Report getPreviousReport(Report currentReport);
    Report getReportForShow(Report report);
    Report getReportByUserByYearByMonth(User user, int year, int month);

    List<Day> allDaysByReport(Report report);

    void fillTheReport(Report report);
    void closeTheReport(Report report);
    void openTheReport(Report report);


    int getPrevFillingDist(List<Day> allDays, Day day);


    List<Point> allPointsByReport(Report report);
    List<Point> allPointsByReport(Report report, AdressesCoordinates arrival);
    List<Point> allPointsByReport(Report report, AdressesCoordinates arrival, LocalDate startDate, LocalDate endDate);



    int getShippingSumm(Report report);


    BigDecimal getTotalSum(Report report);
    BigDecimal getTotalMobile(Report report);
    BigDecimal getTotalAmort(Report report);

    int countReportDistance(Report report);


//    List<Report> getAllReportsByYear(int year);
//    Report getReportByUserByPeriod(User user, String period);
}
