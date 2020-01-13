package com.springapp.mvc.dao;

import com.springapp.mvc.model.Auto;
import com.springapp.mvc.model.User;

import java.util.List;

/**
 * Created by oem on 25.10.18.
 */
public interface AutoDao {
    void saveAuto(Auto auto);
    void updateAuto(Auto auto, User autorizedUser);
    void updateAuto(Auto auto);

    List<Auto> getAllAutos();

    List<Auto> getAllAutosByUser(User user);

    Auto findAutoById(long autoId);

    boolean hasClosedReports(Auto auto);

    List<Auto> findAutoByParam(String brand, String number,
                         Integer AutoYear, String bodyType, Double engineVolume, Integer enginePower, String transmission,
                         String fuelType, String climateMachine, String link, Double linkNorm, User user);
}
