package com.springapp.mvc.service;

import com.springapp.mvc.model.AdressesCoordinates;
import com.springapp.mvc.model.Day;
import com.springapp.mvc.model.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

/**
 * Created by kot on 27.07.17.
 */
public interface PointService {



    void updatePoint(Point point);
    void savePoint(Point point);
    Point findByIdPoint(long id);

    void saveAutoPoint (Point point);

    void deletePoint(long id);
    void calculationCostByZone(Point point, int i, int costByZoneArr);
    Integer knowTheDistance(Point point);

    void addMediumPoint(Point point);

    void tempUpdatePoint(Point point);
    List<Point> getAllPoints();


//    Integer findSameRoute(Point point);
//


//    void setDepartureArrival(Point point);




//    int getNumberOfPointInTheDay(Point point);

}
