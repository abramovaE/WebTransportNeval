package com.springapp.mvc.service;


import com.springapp.mvc.dao.MainSettingsDao;
import com.springapp.mvc.dao.PointDao;
import com.springapp.mvc.model.AdressesCoordinates;
import com.springapp.mvc.model.Day;
import com.springapp.mvc.model.MainSettings;
import com.springapp.mvc.model.Point;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * Created by kot on 27.07.17.
 */
@Service
public class PointServiceImpl implements PointService {

    private PointDao pointDao;
    private MainSettingsDao mainSettingsDao;




    public void setPointDao(PointDao pointDao) {
        this.pointDao = pointDao;
    }


    public void setMainSettingsDao(MainSettingsDao mainSettingsDao) {
        this.mainSettingsDao = mainSettingsDao;
    }

    @Override
    @Transactional
    public void updatePoint(Point point) {
        this.pointDao.updatePoint(point);
    }

    @Override
    @Transactional
    public void tempUpdatePoint(Point point) {
        this.pointDao.tempUpdatePoint(point);
    }

    @Override
    @Transactional
    public void savePoint(Point point) {

        this.pointDao.savePoint(point);
    }

    @Override
    @Transactional
    public void saveAutoPoint(Point point) {
        this.pointDao.saveAutoPoint(point);
    }

    @Override
    @Transactional
    public Point findByIdPoint(long id) {
        return this.pointDao.findByIdPoint(id);
    }

    @Override
    @Transactional
    public List<Point> getAllPoints() {
        return this.pointDao.getAllPoints();
    }

    @Override
    @Transactional
    public void deletePoint(long id) {
        this.pointDao.deletePoint(id);
    }

//    @Override
//    @Transactional
//    public Integer findSameRoute(Point point) {
//        return this.pointDao.findSameRoute(point);
//    }

    @Override
    @Transactional
    public void calculationCostByZone(Point point, int i, int costByZoneArr) {
        MainSettings mainSettings = this.mainSettingsDao.getCurrentMS();
        this.pointDao.calculationCostByZone(point, i, costByZoneArr, mainSettings);
    }

    @Override
    @Transactional
    public Integer knowTheDistance(Point point) {
        return this.pointDao.knowTheDistance(point);
    }


//    @Override
//    @Transactional
//    public int getNumberOfPointInTheDay(Point point) {
//        return this.pointDao.getNumberOfPointInTheDay(point);
//    }


    @Override
    @Transactional
    public void addMediumPoint(Point point) {
        this.pointDao.addMediumPoint(point);
    }


}
