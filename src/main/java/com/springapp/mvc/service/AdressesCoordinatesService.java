package com.springapp.mvc.service;

import com.springapp.mvc.model.AdressesCoordinates;
import com.springapp.mvc.model.MainSettings;
import com.springapp.mvc.model.User;

import java.util.List;

/**
 * Created by kot on 10.07.17.
 */
public interface AdressesCoordinatesService {

    List<AdressesCoordinates> getAllAdresses();
    List<AdressesCoordinates> getAdressesForAutoReports();
    List<AdressesCoordinates> getAllAdresssesWithOfficePriority();

    void addNewAdressCoordinates(AdressesCoordinates adressesCoordinates);
    void updateAdressCoordinates(AdressesCoordinates adressesCoordinates);

    AdressesCoordinates findByIdAdressCoordinates(long id);
    AdressesCoordinates findAdressesCoordinatesByAdress(String adress);


}
