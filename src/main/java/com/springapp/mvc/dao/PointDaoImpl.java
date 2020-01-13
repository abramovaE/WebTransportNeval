package com.springapp.mvc.dao;

import com.springapp.mvc.googleWorker.JsonWorker;
import com.springapp.mvc.model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

import java.util.List;

/**
 * Created by kot on 27.07.17.
 */

@Repository
public class PointDaoImpl implements PointDao {


    private SessionFactory sessionFactory;


    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void updatePoint(Point point) {
        Session session = this.sessionFactory.getCurrentSession();
        List<Point> oldPoints = session.createCriteria(Point.class).add(Restrictions.eq("id", point.getId())).list();
        Point oldPoint = oldPoints.get(0);
        int oldPointDistance = oldPoint.getDistance();
        session.clear();
        Day day = point.getDay();
        Report report = day.getReport();
        if(!report.isClosed()) {
            int newDayDistance = day.getDayDistance() - oldPointDistance + point.getDistance();
            int newReportKmDistance = new BigDecimal((report.getSumKmDistance()*1000 -oldPointDistance + point.getDistance())/1000d).setScale(0, BigDecimal.ROUND_FLOOR).intValue();
            day.setDayDistance(newDayDistance);
            report.setSumKmDistance(newReportKmDistance);
            session.update(point);
            session.update(day);
            session.update(report);
        }



    }

    @Override
    public void tempUpdatePoint(Point point) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(point);
    }

    @Override
    public List<Point> getAllPoints() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Point> points = session.createCriteria(Point.class).list();
        return  points;
    }

    @Override
    public void savePoint(Point point) {
        Session session = this.sessionFactory.getCurrentSession();

        Day day = point.getDay();
        Report report = day.getReport();
        List<Point> pointsOfDay = day.getPoints();



        point.setPosition(pointsOfDay.size());

        if(!report.isClosed()){
            int newDayDistance = day.getDayDistance() + point.getDistance();
            int newReportKmDistance = new BigDecimal((report.getSumKmDistance()*1000 + point.getDistance())/1000d).setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
            day.setDayDistance(newDayDistance);
            report.setSumKmDistance(newReportKmDistance);
            session.save(point);
            session.update(day);
            session.update(report);
        }
    }



    @Override
    public void saveAutoPoint(Point point) {
        Session session = this.sessionFactory.getCurrentSession();

        Day day = point.getDay();
        Report report = day.getReport();




        if(!report.isClosed()){
            int newDayDistance = day.getDayDistance() + point.getDistance();
            int newReportKmDistance = new BigDecimal((report.getSumKmDistance()*1000 + point.getDistance())/1000d).setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
            day.setDayDistance(newDayDistance);
            report.setSumKmDistance(newReportKmDistance);
            session.save(point);
            session.update(day);
            session.update(report);
        }
    }


    @Override
    public Point findByIdPoint(long id) {
        Session session = this.sessionFactory.getCurrentSession();
        Point point = (Point) session.get(Point.class, new Long(id));
        return point;
    }



//    @Override
//    public List<Point> getAllPoints() {
//        Session session = this.sessionFactory.getCurrentSession();
//        List<Point> allRouteByDayRoutes = session.createQuery("from Point").list();
//        return allRouteByDayRoutes;
//    }

    @Override
    public void deletePoint(long id) {
//        System.out.println("start");
        Session session = this.sessionFactory.getCurrentSession();
        Point point = (Point) session.load(Point.class, new Long(id));
        Day day= point.getDay();
        Report report = day.getReport();




        if(point != null && !report.isClosed()){
            int pointDistance = point.getDistance();
            int newDayDistance = day.getDayDistance() - pointDistance;
            int newReportKmDistance = new BigDecimal((report.getSumKmDistance()*1000 - pointDistance)/1000d).setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
            day.setDayDistance(newDayDistance);
            report.setSumKmDistance(newReportKmDistance);

            session.delete(point);
            session.update(day);
            session.update(report);
            session.flush();

            List<Point> dayPoints = session.createCriteria(Point.class).add(Restrictions.eq("day", day)).list();
            for(Point p: dayPoints){
                int newPosition = dayPoints.indexOf(p);
                p.setPosition(newPosition);
                session.update(p);
            }
        }



    }


    @Override
    public void addMediumPoint(Point point) {
        Session session = this.sessionFactory.getCurrentSession();
        Day day = point.getDay();
        Report report = day.getReport();
        List<Point> pointsOfDay = day.getPoints();

        if(!report.isClosed()){
            for(Point p: pointsOfDay){
                if(p.getPosition() >= point.getPosition()){
                    p.setPosition(p.getPosition() + 1);
                    session.update(p);
                }
            }
            int newDayDistance = day.getDayDistance() + point.getDistance();
            int newReportKmDistance = new BigDecimal((report.getSumKmDistance()*1000 + point.getDistance())/1000d).setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
            day.setDayDistance(newDayDistance);
            report.setSumKmDistance(newReportKmDistance);
            session.save(point);
            session.update(day);
            session.update(report);
        }
    }




    //    @Override
    private Integer findSameRoute(Point point) {
        Session session = this.sessionFactory.getCurrentSession();

        List<Point> points = session.createCriteria(Point.class)
                .add(Restrictions.eq("departure", point.getDeparture()))
                .add(Restrictions.eq("arrival", point.getArrival()))
                .add(Restrictions.eq("zsd", point.isZsd()))
                .list();
        if(!points.isEmpty()) {
            return points.get(0).getDistance();
        }

        else {
            return null;
        }
    }

    @Override
    public void calculationCostByZone(Point point, int i, int costByZoneArr, MainSettings currentMS) {

        String officeAdress = currentMS.getTechOffice().getAddress();

        AdressesCoordinates arrival = point.getArrival();
        AdressesCoordinates departure = point.getDeparture();

        int numberOfZoneArr = arrival.getNumberOfZone();
        int numberOfZoneDep = departure.getNumberOfZone();

//                Первая поездка - полная стоимость зоны
        if (i == 0) {
            point.setCostByZone(Double.valueOf(costByZoneArr));
        } else {
//                        Поездка в офис - бесплатно
            if (arrival.getAddress().equals(officeAdress)) {
                point.setCostByZone(Double.valueOf(0));
            }

//                        Любой старт из офиса - полная стоимость зоны
//                        Если стартуем из восьмой зоны, считаем как выезд из офиса (то есть не половиним стоимость проезда)
            else if ((departure.getAddress().equals(officeAdress)) || (numberOfZoneDep == 8 && numberOfZoneArr != 1)) {
                point.setCostByZone(Double.valueOf(costByZoneArr));
            }

//                        Перемещение из 8-й зоны в 1-ю = 1/2 стоимости первой зоны
            else if (numberOfZoneDep == 8 && numberOfZoneArr == 1) {
                point.setCostByZone(Double.valueOf(costByZoneArr / 2));
            }

//                        Перемещение внутри зоны - 100 руб.
            else if (numberOfZoneDep == numberOfZoneArr) {
                point.setCostByZone(Double.valueOf(100));
            }

//                        Перемещение из зоны в зону без заезда в офис - половина стоимости зоны назначения
            else if (!departure.getAddress().equals(officeAdress) && !arrival.getAddress().equals(officeAdress)) {
                point.setCostByZone(Double.valueOf(costByZoneArr / 2));
            }

        }

    }



    @Override
    public Integer knowTheDistance(Point point){
        Integer distance;
        String originCoords = point.getDeparture().getCoordinates();
        String destinationCoords = point.getArrival().getCoordinates();


//        if(originCoords!=null && destinationCoords !=null){
            Integer sameRoute = findSameRoute(point);
            if(sameRoute != null && sameRoute != 0){
                distance = sameRoute;
            }
            else {
                if(point.isZsd()){
                    distance = JsonWorker.knowTheDistanceWithZsd(originCoords, destinationCoords);
                }
                else {
                    distance = JsonWorker.knowTheDistance(originCoords, destinationCoords);
                }
            }

//        }


        return distance;
    }


}