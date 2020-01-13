package com.springapp.mvc.service;

import com.springapp.mvc.dao.DayDao;
import com.springapp.mvc.model.AdressesCoordinates;
import com.springapp.mvc.model.Day;
import com.springapp.mvc.model.Point;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by kot on 12.07.17.
 */
public class DayServiceImpl implements DayService {

    private DayDao dayDao;

    public void setDayDao(DayDao dayDao) {
        this.dayDao = dayDao;
    }

    @Override
    @Transactional
    public int countDayDistance(Day day) {
        return this.dayDao.countDayDistance(day);
    }

    @Override
    @Transactional
    public void updateDay(Day day) {
        this.dayDao.updateDay(day);

    }
    @Override
    @Transactional
    public void updateDay(Day day, Boolean canUpdate) {
        this.dayDao.updateDay(day, canUpdate);

    }
    @Override
    @Transactional
    public void saveDay(Day day) {

        this.dayDao.saveDay(day);
    }

    @Override
    @Transactional
    public Day findByIdDay(long id) {
        return this.dayDao.findByIdDay(id);
    }

    @Override
    @Transactional
    public void deleteDay(long id) {
        this.dayDao.deleteDay(id);
    }


    @Override
    @Transactional
    public List<Point> allPointsByDay(Day day) {
        return this.dayDao.allPointsByDay(day);
    }

    @Override
    @Transactional
    public List<Point> allPointsByDayWithArrival(Day day, AdressesCoordinates arrival) {
        return  this.dayDao.allPointsByDayWithArrival(day, arrival);
    }


    //    @Override
//    @Transactional
//    public int getNumberOfPointInTheDay(Point point, Day day) {
//        return this.dayDao.getNumberOfPointInTheDay(point, day);
//    }

//    @Override
//    @Transactional
//    public int calcAndSetKmDistanceForDay(long dayId) {
//        return  this.dayDao.calcAndSetKmDistanceForDay(dayId);
//    }
}
