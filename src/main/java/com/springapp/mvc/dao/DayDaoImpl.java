package com.springapp.mvc.dao;

import com.springapp.mvc.model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * Created by kot on 12.07.17.
 */

@Repository
public class DayDaoImpl implements DayDao {


    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public void updateDay(Day day) {

        Session session = this.sessionFactory.getCurrentSession();
        Report report = day.getReport();

        if(!report.isClosed()) {
            List<Day> oldDays = session.createCriteria(Day.class).add(Restrictions.eq("id", day.getId())).list();
            Day oldDay = oldDays.get(0);
            Double oldDaySumm = oldDay.getSumm();
            if(oldDaySumm == null){
                oldDaySumm = 0d;
            }
            session.clear();
            Double daySumm = day.getSumm();
            if(daySumm == null) {
                daySumm = 0d;
            }

            Double oldReportSumSumm = report.getSumSumm();
            if(oldReportSumSumm == null){
                oldReportSumSumm = 0d;
            }

            BigDecimal newReportSumSumm = new BigDecimal(oldReportSumSumm).subtract(new BigDecimal(oldDaySumm)).add(new BigDecimal(daySumm)).setScale(2, RoundingMode.HALF_UP);
            report.setSumSumm(newReportSumSumm.doubleValue());
            session.update(day);
            session.update(report);
            calcAndSetMediumFuelPriceForReport(report);
        }



    }

    @Override
    public int countDayDistance(Day day) {

        List<Point> points = allPointsByDay(day);
        int distance = 0;
        for(Point p: points){
            distance += p.getDistance();
        }
        return distance;

    }

    @Override
    public void updateDay(Day day, Boolean canUpdate) {

        Session session = this.sessionFactory.getCurrentSession();
        Report report = day.getReport();

        if(!report.isClosed()) {
            List<Day> oldDays = session.createCriteria(Day.class).add(Restrictions.eq("id", day.getId())).list();
            Day oldDay = oldDays.get(0);
            Double oldDaySumm = oldDay.getSumm();
            if(oldDaySumm == null){
                oldDaySumm = 0d;
            }
            session.clear();
            Double daySumm = day.getSumm();
            if(daySumm == null) {
                daySumm = 0d;
            }

            Double oldReportSumSumm = report.getSumSumm();
            if(oldReportSumSumm == null){
                oldReportSumSumm = 0d;
            }

            BigDecimal newReportSumSumm = new BigDecimal(oldReportSumSumm).subtract(new BigDecimal(oldDaySumm)).add(new BigDecimal(daySumm)).setScale(2, RoundingMode.HALF_UP);
            report.setSumSumm(newReportSumSumm.doubleValue());
            session.update(day);
            session.update(report);
            calcAndSetMediumFuelPriceForReport(report);
        }
        else {
            List<Day> oldDays = session.createCriteria(Day.class).add(Restrictions.eq("id", day.getId())).list();
            Day oldDay = oldDays.get(0);
            Double oldDaySumm = oldDay.getSumm();
            if(oldDaySumm == null){
                oldDaySumm = 0d;
            }
            session.clear();
            Double daySumm = day.getSumm();
            if(daySumm == null) {
                daySumm = 0d;
            }

            Double oldReportSumSumm = report.getSumSumm();
            if(oldReportSumSumm == null){
                oldReportSumSumm = 0d;
            }

            BigDecimal newReportSumSumm = new BigDecimal(oldReportSumSumm).subtract(new BigDecimal(oldDaySumm)).add(new BigDecimal(daySumm)).setScale(2, RoundingMode.HALF_UP);
            report.setSumSumm(newReportSumSumm.doubleValue());
            session.update(day);
            session.update(report);
            BigDecimal mfp = calcAndSetMediumFuelPriceForReport(report);


            BlockedReportData blockedReportData = report.getBlockedReportData();
//            System.out.println(blockedReportData);
            blockedReportData.setSumSumm(newReportSumSumm.doubleValue());


//            System.out.println(report.getMediumFuelPrice());
            blockedReportData.setMediumFuelPrice(mfp.doubleValue());
            session.update(blockedReportData);

        }

    }

    @Override
    public void saveDay(Day day) {
        Session session = this.sessionFactory.getCurrentSession();
        Report report = day.getReport();
        if(!report.isClosed()){

            Double daySumm = day.getSumm();
            if(daySumm != null){
                Double oldReportSumSumm = report.getSumSumm();
                if(oldReportSumSumm == null){
                    oldReportSumSumm = 0d;
                }
                BigDecimal newReportSumSumm = new BigDecimal(oldReportSumSumm).add(new BigDecimal(daySumm)).setScale(2, RoundingMode.HALF_UP);
                report.setSumSumm(newReportSumSumm.doubleValue());
            }
            session.save(day);
            session.update(report);
            calcAndSetMediumFuelPriceForReport(report);
        }







    }

    @Override
    public Day findByIdDay(long id) {
        Session session = this.sessionFactory.getCurrentSession();
        Day day = (Day) session.get(Day.class, new Long(id));
        return day;
    }

    @Override
    public void deleteDay(long id) {
        Session session = this.sessionFactory.getCurrentSession();
        Day day = (Day) session.load(Day.class, new Long(id));
        Report report = day.getReport();
        if(day != null && !report.isClosed()){

            Double daySumm = day.getSumm();
            if(daySumm != null){
                Double oldReportSumSumm = report.getSumSumm();
                if(oldReportSumSumm == null){
                    oldReportSumSumm = 0d;
                }

                BigDecimal newReportSumSumm = new BigDecimal(oldReportSumSumm).subtract(new BigDecimal(daySumm)).setScale(2, RoundingMode.HALF_UP);
                report.setSumSumm(newReportSumSumm.doubleValue());
            }
            int dayDistance = day.getDayDistance();
            int newReportKmDistance = new BigDecimal((report.getSumKmDistance()*1000 - dayDistance)/1000d).setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
            report.setSumKmDistance(newReportKmDistance);
            session.delete(day);
            session.update(report);
            calcAndSetMediumFuelPriceForReport(report);
        }
    }


    @Override
    public List<Point> allPointsByDay(Day day) {
        Session session = this.sessionFactory.getCurrentSession();
        List<Point> points = session.createCriteria(Point.class).add(Restrictions.eq("day", day)).addOrder(Order.asc("position")).list();
        return points;
    }


    @Override
    public List<Point> allPointsByDayWithArrival(Day day, AdressesCoordinates arrival) {
        Session session = this.sessionFactory.getCurrentSession();
        List<Point> points = session.createCriteria(Point.class).add(Restrictions.eq("day", day))
                .add(Restrictions.eq("arrival", arrival)).addOrder(Order.asc("position")).list();
        return points;
    }


//    @Override
//    public int getNumberOfPointInTheDay(Point point, Day day) {
//        List<Point> points = allPointsByDay(day);
//        return  points.indexOf(point);
//    }



    private BigDecimal calcAndSetMediumFuelPriceForReport(Report report) {
        BigDecimal totalMonthSum = new BigDecimal("0");
        BigDecimal totalLiters = new BigDecimal("0");
        Session session = this.sessionFactory.getCurrentSession();
        List<Day> days = session.createCriteria(Day.class).add(Restrictions.eq("report", report)).list();
        for(Day day:days){
            if(day.getSumm() != null && day.getCostByLiter() != null){
                BigDecimal daySum = new BigDecimal(day.getSumm()).setScale(2, RoundingMode.HALF_UP);
                BigDecimal costByLiter = new BigDecimal(day.getCostByLiter()).setScale(2, RoundingMode.HALF_UP);
                totalMonthSum = totalMonthSum.add(daySum).setScale(2, RoundingMode.HALF_UP);
                BigDecimal litersByDay = daySum.divide(costByLiter, 2, RoundingMode.HALF_UP);
                totalLiters = totalLiters.add(litersByDay);
            }
        }
        if(totalLiters.doubleValue() == 0d){
            return new BigDecimal(0);
        }

        BigDecimal mfp =  totalMonthSum.divide(totalLiters, 2, BigDecimal.ROUND_HALF_UP);
        report.setMediumFuelPrice(mfp.doubleValue());
        session.update(report);

        return mfp;
}


}
