package com.springapp.mvc.dao;

import com.springapp.mvc.model.Day;
import com.springapp.mvc.model.MainSettings;
import com.springapp.mvc.model.Point;

import java.util.List;

/**
 * Created by kot on 27.07.17.
 */


public interface PointDao {

    void updatePoint(Point point);
    void savePoint (Point point);
    void saveAutoPoint (Point point);

    Point findByIdPoint(long id);
    void deletePoint(long id);
    void calculationCostByZone(Point point, int i, int costByZoneArr, MainSettings currentMS);
    Integer knowTheDistance(Point point);

    void addMediumPoint(Point point);

    void tempUpdatePoint(Point point);


    List<Point> getAllPoints();



}
