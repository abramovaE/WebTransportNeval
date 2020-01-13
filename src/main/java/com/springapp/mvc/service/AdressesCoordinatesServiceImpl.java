package com.springapp.mvc.service;

import com.springapp.mvc.dao.AdressesCoordinatesDao;
import com.springapp.mvc.dao.MainSettingsDao;
import com.springapp.mvc.model.AdressesCoordinates;
import com.springapp.mvc.model.MainSettings;
import com.springapp.mvc.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

/**
 * Created by kot on 10.07.17.
 */

@Service
public class AdressesCoordinatesServiceImpl implements  AdressesCoordinatesService {

    private AdressesCoordinatesDao adressesCoordinatesDao;
    private MainSettingsDao mainSettingsDao;

    public void setAdressesCoordinatesDao(AdressesCoordinatesDao adressesCoordinatesDao) {
        this.adressesCoordinatesDao = adressesCoordinatesDao;
    }

    public void setMainSettingsDao(MainSettingsDao mainSettingsDao) {
        this.mainSettingsDao = mainSettingsDao;
    }

    @Override
    @Transactional
    public List<AdressesCoordinates> getAllAdresses() {
        return this.adressesCoordinatesDao.getAllAdresses();
    }

    @Override
    @Transactional
    public List<AdressesCoordinates> getAdressesForAutoReports() {
        return this.adressesCoordinatesDao.getAdressesForAutoReports();
    }


    @Override
    @Transactional
    public void addNewAdressCoordinates(AdressesCoordinates adressesCoordinates) {
        this.adressesCoordinatesDao.addNewAdressCoordinates(adressesCoordinates);
    }


    @Override
    @Transactional
    public void updateAdressCoordinates(AdressesCoordinates adressesCoordinates) {
        this.adressesCoordinatesDao.updateAdressCoordinates(adressesCoordinates);
    }

    @Override
    @Transactional
    public AdressesCoordinates findByIdAdressCoordinates(long id) {
        return this.adressesCoordinatesDao.findByIdAdressCoordinates(id);
    }

    @Override
    @Transactional
    public AdressesCoordinates findAdressesCoordinatesByAdress(String adress) {
        return this.adressesCoordinatesDao.findAdressesCoordinatesByAdress(adress);
    }

    @Override
    @Transactional
    public List<AdressesCoordinates> getAllAdresssesWithOfficePriority() {
        MainSettings currentMS = mainSettingsDao.getCurrentMS();
        return this.adressesCoordinatesDao.getAllAdresssesWithOfficePriority(currentMS);
    }


}
