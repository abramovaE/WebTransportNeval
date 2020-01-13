package com.springapp.mvc.service;

import com.springapp.mvc.model.MainSettings;

import java.util.List;

/**
 * Created by oem on 11.10.18.
 */
public interface MainSettingsService {

//    List<MainSettings> getAllMS();
    void saveMS(MainSettings mainSettings);
    void updateMS(MainSettings mainSettings);

    MainSettings getCurrentMS();
    MainSettings findMSById(long id);
}
