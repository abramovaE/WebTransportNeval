package com.springapp.mvc.service;

import com.springapp.mvc.dao.AutoDao;
import com.springapp.mvc.model.Auto;
import com.springapp.mvc.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by oem on 25.10.18.
 */
@Service
public class AutoServiceImpl implements AutoService {
    private AutoDao autoDao;

    public void setAutoDao(AutoDao autoDao) {
        this.autoDao = autoDao;
    }

    @Override
    @Transactional
    public void saveAuto(Auto auto) {
        this.autoDao.saveAuto(auto);
    }

    @Override
    @Transactional
    public void updateAuto(Auto auto, User autorizedUser) {
        this.autoDao.updateAuto(auto, autorizedUser);
    }

    @Override
    @Transactional
    public List<Auto> getAllAutos() {
        return this.autoDao.getAllAutos();
    }

    @Override
    @Transactional
    public List<Auto> getAllAutosByUser(User user) {
        return this.autoDao.getAllAutosByUser(user);
    }

    @Override
    @Transactional
    public Auto findAutoById(long autoId) {
        return this.autoDao.findAutoById(autoId);
    }

    @Override
    @Transactional
    public boolean hasClosedReports(Auto auto) {
        return this.autoDao.hasClosedReports(auto);
    }

    @Override
    @Transactional
    public List<Auto> findAutoByParam(String brand, String number, Integer autoYear, String bodyType, Double engineVolume,
                                Integer enginePower, String transmission, String fuelType, String climateMachine, String link, Double linkNorm, User user) {
        return this.autoDao.findAutoByParam(brand, number, autoYear, bodyType, engineVolume,
                enginePower,transmission, fuelType, climateMachine, link, linkNorm, user);
    }

    @Override
    @Transactional
    public void updateAuto(Auto auto){
        this.autoDao.updateAuto(auto);
    }

}
