package com.springapp.mvc.dao;



import com.springapp.mvc.model.*;
import com.springapp.mvc.enums.AccountancyTypes;
import com.springapp.mvc.enums.BodyTypes;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.*;


/**
 * Created by kot on 10.07.17.
 */

@Repository
public class ReportDaoImpl implements ReportDao {

    private SessionFactory sessionFactory;
    private final static int SHIPPING_PER_DAY_PRICE = 500;
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void saveReport(Report report) {
        Session session = this.sessionFactory.getCurrentSession();
        session.save(report);
    }

    @Override
    public void updateReport(Report report) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(report);
    }

    @Override
    public Report findByIdReport(long id) {
        Session session = sessionFactory.getCurrentSession();
        Report report = (Report) session.get(Report.class, new Long(id));
        return report;
    }

    @Override
    public void deleteReport(long id) {
        Session session = sessionFactory.getCurrentSession();
        Report report = (Report) session.get(Report.class, new Long(id));
        if(report != null && !report.isClosed()){
            session.delete(report);
        }
    }



    @Override
    public List<Report> getAllReports() {
        Session session = sessionFactory.getCurrentSession();
        List<Report> reports = session.createCriteria(Report.class)
                .addOrder(Order.desc("year"))
                .addOrder(Order.desc("monthNumber"))
                .list();
        return reports;
    }



    @Override
    public List<Report> getAllReportsByYearByMonth(int year, int month) {
        Session session = sessionFactory.getCurrentSession();
        List<Report> reports = session.createCriteria(Report.class)
                .add(Restrictions.eq("year", year))
                .add(Restrictions.eq("monthNumber", month))
                .addOrder(Order.asc("isClosed"))
                .createCriteria("user", JoinType.INNER_JOIN)
                .addOrder(Order.asc("surname"))
                .addOrder(Order.asc("name"))
                .addOrder(Order.asc("patronymic"))
                .list();
        return reports;
    }

    @Override
    public List<Report> getAllReportsByUser(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        List<Report> reports = session.createCriteria(Report.class)
                .add(Restrictions.eq("user", user))
                .addOrder(Order.asc("isClosed"))
                .list();
        return reports;
    }



    @Override
    public Report getPreviousReport(Report currentReport) {
        Session session = this.sessionFactory.getCurrentSession();
        YearMonth currentYM = currentReport.getYearMonth();
        List<Report> res;
        do {
            currentYM = currentYM.minusMonths(1);
            res = session.createCriteria(Report.class)
                    .add(Restrictions.eq("user", currentReport.getUser()))
                    .add(Restrictions.eq("year", currentYM.getYear()))
                    .add(Restrictions.eq("monthNumber", currentYM.getMonthValue())).list();
        }

        while (res.isEmpty() && currentYM.getYear() > 2016);

        Report r = null;
        if(!res.isEmpty()){
            r = res.get(0);
            if(r.isClosed()) {
                return getReportForShow(r);
            }
        }
        return r;
    }



    @Override
    public List<Day> allDaysByReport(Report report) {
        Session session = sessionFactory.getCurrentSession();
        List<Day> days = session.createCriteria(Day.class).add(Restrictions.eq("report", report)).addOrder(Order.asc("dayNumber")).list();

//        List<Day> days = session.createCriteria(Day.class).add(Restrictions.eq("report", report)).addOrder(Order.asc("date")).list();
        return days;
    }




    @Override
    public List<Point> allPointsByReport(Report report) {
        Session session = sessionFactory.getCurrentSession();
        List<Point> points = session.createCriteria(Point.class).
                createCriteria("day", JoinType.INNER_JOIN).
                add(Restrictions.eq("report", report)).list();
        return points;


    }


    @Override
    public List<Point> allPointsByReport(Report report, AdressesCoordinates arrival) {
//        Session session = sessionFactory.getCurrentSession();
//        List<Day> days = session.createCriteria(Day.class).add(Restrictions.eq("report", report)).list();
//        List<Point> points = new ArrayList<>();
//        for(Day day: days){
//            points.addAll(session.createCriteria(Point.class).add(Restrictions.eq("day", day)).add(Restrictions.eq("arrival", arrival)).list());
//        }
//        return points;


        Session session = sessionFactory.getCurrentSession();
        List<Point> points = session.createCriteria(Point.class).
                add(Restrictions.eq("arrival", arrival)).
                createCriteria("day", JoinType.INNER_JOIN).
                add(Restrictions.eq("report", report)).list();
        return points;


    }

    @Override
    public List<Point> allPointsByReport(Report report, AdressesCoordinates arrival, LocalDate startDate, LocalDate endDate) {
        Session session = sessionFactory.getCurrentSession();
        List<Day> days = session.createCriteria(Day.class).add(Restrictions.eq("report", report)).list();
        List<Point> points = new ArrayList<>();
        for(Day day: days){

//            LocalDate dayDate = LocalDate.parse(day.getDate(), dateTimeFormatter);
//            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

            LocalDate dayDate = LocalDate.of(report.getYear(), report.getMonthNumber(), day.getDayNumber());



            if((dayDate.isEqual(startDate) || dayDate.isAfter(startDate)) && (dayDate.isBefore(endDate) || dayDate.isEqual(endDate))){
                points.addAll(session.createCriteria(Point.class).add(Restrictions.eq("day", day)).add(Restrictions.eq("arrival", arrival)).list());
            }
        }
        return points;
    }

    @Override
    public int getPrevFillingDist(List<Day> days, Day day) {
        List<Day> allDays = new ArrayList<>();
        allDays.addAll(days);

        Collections.sort(allDays, new Comparator<Day>() {
            @Override
            public int compare(Day o1, Day o2) {
                return o2.getDayNumber() - o1.getDayNumber();
            }
        });

        Session session = sessionFactory.getCurrentSession();
        Report curReport = day.getReport();

        if(allDays.get(allDays.size()-1).getSumm() == null || allDays.get(allDays.size()-1).getSumm() == 0){
                List<Day> allPrevDays = (List<Day>) session.createCriteria(Day.class)
                        .add(Restrictions.eq("report", getPreviousReport(curReport)))
                        .addOrder(Order.desc("dayNumber")).list();
                for(Day d: allPrevDays){
                    allDays.add(d);
                    if(d.getSumm() != null && d.getSumm() != 0){
                        break;
                    }
                }
            }
        int prevDist = 0;
        int dayIndex = allDays.indexOf(day);
        for(int i=dayIndex+1; i<allDays.size(); i++)
        {
            prevDist += allDays.get(i).getDayDistance();
            if(allDays.get(i).getSumm() != null && allDays.get(i).getSumm() != 0){
                break;
            }
        }
        return prevDist;
    }


    @Override
    public int countReportDistance(Report report) {
        int distance = 0;

        for(Day day: allDaysByReport(report)){
            distance += day.getDayDistance()/1000;
        }


        return distance;
    }

    @Override
    public void fillTheReport(Report report) {

        Session session = this.sessionFactory.getCurrentSession();
        List<Day> days = session.createCriteria(Day.class).add(Restrictions.eq("report", report)).list();


                if(report.getMediumFuelPrice() == null){
                    BigDecimal mfp =  getMediumFuelPrice(days);
                    report.setMediumFuelPrice(mfp.doubleValue());
                    session.clear();
                    session.update(report);
                }


                if(report.isClosed()){
                    report = getReportForShow(report);
                    BlockedReportData blockedReportData = report.getBlockedReportData();

                    if(blockedReportData.getMediumFuelPrice() == null){
                        BigDecimal mfp =  getMediumFuelPrice(days);
                        blockedReportData.setMediumFuelPrice(mfp.doubleValue());
                        session.clear();
                        session.update(blockedReportData);
                    }
                }
    }

    private BigDecimal getMediumFuelPrice(List<Day> days){
        BigDecimal totalMonthSum = new BigDecimal("0");
        BigDecimal totalLiters = new BigDecimal("0");
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
        return mfp;

    }

    @Override
    public void closeTheReport(Report report) {
        Session session = this.sessionFactory.getCurrentSession();
        BlockedReportData blockedReportData = report.getBlockedReportData();

        if(blockedReportData != null){
            report.setIsClosed(true);
            session.update(blockedReportData);
        }

        else {
            blockedReportData = new BlockedReportData(report);
            report.setBlockedReportData(blockedReportData);
            report.setIsClosed(true);
            session.save(blockedReportData);
        }

        session.update(report);
        session.flush();
    }

    @Override
    public Report getReportForShow(Report report) {
        if(report.getAuto() == null && !report.isClosed()){
            Auto auto = report.getUser().getCurrentAuto();
            report.setAuto(auto);
            updateReport(report);
        }

        if(report.isClosed()){
            User user = report.getUser();
            Auto auto = report.getAuto();
            BlockedReportData blockedReportData = report.getBlockedReportData();

                if(auto == null){
                    auto = new Auto();

                }

                user.setLogin(blockedReportData.getLogin());
                user.setName(blockedReportData.getName());
                user.setPatronymic(blockedReportData.getPatronymic());
                user.setSurname(blockedReportData.getSurname());
                user.setPost(blockedReportData.getPost());
                user.setTransponder(blockedReportData.getTransponder());
                user.setAccountancyType(blockedReportData.getAccountancyType());
                user.setAmortizacia(blockedReportData.getAmortizacia());

//                auto.setSummerNorm(blockedReportData.getSummerNorm());
//                auto.setWinterNorm(blockedReportData.getWinterNorm());

                if(report.getAuto() == null || !report.getAuto().equals(auto)){
                    Session session = sessionFactory.getCurrentSession();
                    session.save(auto);
                    report.setAuto(auto);
                }
        }
    return report;
    }










    @Override
    public Report getReportByUserByYearByMonth(User user, int year, int month) {
        Session session = this.sessionFactory.getCurrentSession();
        Report report = (Report) session.createCriteria(Report.class)
                .add(Restrictions.eq("user", user))
                .add(Restrictions.eq("year", year))
                .add(Restrictions.eq("monthNumber", month))
                .uniqueResult();
        return report;
    }



    @Override
    public int getShippingSumm(Report report) {
        Session session = this.sessionFactory.getCurrentSession();
        List<Day> days = session.createCriteria(Day.class).add(Restrictions.eq("report", report)).add(Restrictions.eq("shipping", true)).list();
        return days.size();


    }

    @Override
    public BigDecimal getTotalSum(Report report) {
        Session session = this.sessionFactory.getCurrentSession();
        List<User> users = session.createCriteria(User.class).add(Restrictions.eq("id", report.getUser().getId())).list();
        BigDecimal totalSum = new BigDecimal(0);
        if(users.size() > 0){
            User user = users.get(0);
            if(user.getAccountancyType() != null && user.getAccountancyType().equals(AccountancyTypes.CHECK.getName())){
                    if(report.getSumSumm() != null) {
                        totalSum = new BigDecimal(report.getSumSumm()).setScale(2, BigDecimal.ROUND_HALF_UP).add(getTotalMobile(report));
                    }
            }
            else if(user.getAccountancyType() != null && user.getAccountancyType().equals(AccountancyTypes.ODOM.getName())){
                    totalSum = report.getCompany().add(getTotalMobile(report)).add(getTotalAmort(report));
            }
        }

        if(report.getAuto() != null && report.getAuto().getBodyType() != null &&
                report.getAuto().getBodyType().equals(BodyTypes.MINIBUS.getName())){
            totalSum = totalSum.add(new BigDecimal(SHIPPING_PER_DAY_PRICE * this.getShippingSumm(report)));
        }
        return totalSum.setScale(1, BigDecimal.ROUND_HALF_UP);
    }




    @Override
    public BigDecimal getTotalMobile(Report report) {

        if (report.getMobile() != null){
            return new BigDecimal(report.getMobile());
        }

        BigDecimal totalMobile = new BigDecimal(report.getMobileWeeks()).multiply(new BigDecimal(100));
        return totalMobile.setScale(1, BigDecimal.ROUND_HALF_UP);
    }


    @Override
    public BigDecimal getTotalAmort(Report report) {
        BigDecimal totalAmort = new BigDecimal(0);

        if(report.getUser().getAmortizacia() != null){
            BigDecimal amort = new BigDecimal(report.getUser().getAmortizacia()).setScale(1, BigDecimal.ROUND_HALF_UP);
            totalAmort = new BigDecimal(report.getSumKmDistance()).multiply(amort);
        }
        return totalAmort.setScale(1, BigDecimal.ROUND_HALF_UP);
    }
}

