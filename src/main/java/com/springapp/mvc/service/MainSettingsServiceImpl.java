package com.springapp.mvc.service;

import com.springapp.mvc.dao.MainSettingsDao;
import com.springapp.mvc.model.MainSettings;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by oem on 11.10.18.
 */

@Service
public class MainSettingsServiceImpl implements MainSettingsService {

    private MainSettingsDao mainSettingsDao;

    public void setMainSettingsDao(MainSettingsDao mainSettingsDao) {
        this.mainSettingsDao = mainSettingsDao;
    }

//    @Override
//    @Transactional
//    public List<MainSettings> getAllMS() {
//        return null;
//    }

    @Override
    @Transactional
    public void saveMS(MainSettings mainSettings) {
        this.mainSettingsDao.saveMS(mainSettings);
    }

    @Override
    @Transactional
    public void updateMS(MainSettings mainSettings) {
        this.mainSettingsDao.updateMS(mainSettings);

    }

    @Override
    @Transactional
    public MainSettings getCurrentMS() {
        return mainSettingsDao.getCurrentMS();
    }

    @Override
    @Transactional
    public MainSettings findMSById(long id) {
        return mainSettingsDao.findMSById(id);
    }
}
