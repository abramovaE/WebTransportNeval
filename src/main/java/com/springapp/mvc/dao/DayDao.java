package com.springapp.mvc.dao;

import com.springapp.mvc.model.AdressesCoordinates;
import com.springapp.mvc.model.Day;
import com.springapp.mvc.model.Point;

import java.util.List;

/**
 * Created by kot on 12.07.17.
 */
public interface DayDao {
    void updateDay(Day day);
    void updateDay(Day day,Boolean canUpdate);
    void saveDay(Day day);
    Day findByIdDay(long id);
    void deleteDay(long id);
    List<Point> allPointsByDay(Day day);
    List<Point> allPointsByDayWithArrival(Day day, AdressesCoordinates arrival);


    int countDayDistance(Day day);

//    int getNumberOfPointInTheDay(Point point, Day day);

//    Day getPrevDay(Day day);


//    int calcAndSetKmDistanceForDay(long dayId);


}
