package com.springapp.mvc.service;

import com.springapp.mvc.dao.ReportDao;
import com.springapp.mvc.model.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by kot on 10.07.17.
 */

@Service
public class ReportServiceImpl implements  ReportService {

    private ReportDao reportDao;

    public void setReportDao(ReportDao reportDao) {
        this.reportDao = reportDao;
    }

    @Override
    @Transactional
    public void saveReport(Report report) {
        this.reportDao.saveReport(report);
    }

    @Override
    @Transactional
    public void updateReport(Report report) {
        this.reportDao.updateReport(report);
    }

    @Override
    @Transactional
    public Report findByIdReport(long id) {
        return this.reportDao.findByIdReport(id);
    }

    @Override
    @Transactional
    public void deleteReport(long id) {
        this.reportDao.deleteReport(id);
    }


    @Override
    @Transactional
    public int getShippingSumm(Report report) {
        return this.reportDao.getShippingSumm(report);
    }

    @Override
    @Transactional
    public List<Report> getAllReports() {
        return this.reportDao.getAllReports();
    }


    @Override
    @Transactional
    public List<Report> getAllReportsByYearByMonth(int year, int month) {
        return this.reportDao.getAllReportsByYearByMonth(year, month);
    }

    @Override
    @Transactional
    public List<Report> getAllReportsByUser(User user) {
        return this.reportDao.getAllReportsByUser(user);
    }



    @Override
    @Transactional
    public Report getPreviousReport(Report currentReport) {
        return this.reportDao.getPreviousReport(currentReport);
    }



    @Override
    @Transactional
    public List<Day> allDaysByReport(Report report) {
        return this.reportDao.allDaysByReport(report);
    }


    @Override
    public int countReportDistance(Report report) {
        return this.reportDao.countReportDistance(report);
    }

    @Override
    @Transactional
    public void fillTheReport(Report report) {
        this.reportDao.fillTheReport(report);
    }

    @Override
    @Transactional
    public void closeTheReport(Report report) {
        this.reportDao.closeTheReport(report);
    }

    @Override
    @Transactional
    public void openTheReport(Report report) {
        this.reportDao.openTheReport(report);
    }

    @Override
    @Transactional
    public Report getReportForShow(Report report) {
        return this.reportDao.getReportForShow(report);
    }

    @Override
    @Transactional
    public int getPrevFillingDist(List<Day> allDays, Day day) {
        return this.reportDao.getPrevFillingDist(allDays, day);
    }

    @Override
    @Transactional
    public Report getReportByUserByYearByMonth(User user, int year, int month) {
        return this.reportDao.getReportByUserByYearByMonth(user, year, month);
    }


    @Override
    @Transactional
    public List<Point> allPointsByReport(Report report, AdressesCoordinates arrival) {
        return this.reportDao.allPointsByReport(report, arrival);
    }

    @Override
    @Transactional
    public List<Point> allPointsByReport(Report report, AdressesCoordinates arrival, LocalDate startDate, LocalDate endDate) {
        return this.reportDao.allPointsByReport(report, arrival, startDate, endDate);
    }

    @Override
    @Transactional
    public BigDecimal getTotalSum(Report report) {
        return this.reportDao.getTotalSum(report);
    }

    @Override
    @Transactional
    public BigDecimal getTotalMobile(Report report) {
        return this.reportDao.getTotalMobile(report);
    }

    @Override
    @Transactional
    public BigDecimal getTotalAmort(Report report) {
        return this.reportDao.getTotalAmort(report);
    }

    @Override
    @Transactional
    public List<Point> allPointsByReport(Report report) {
        return this.reportDao.allPointsByReport(report);
    }
}
