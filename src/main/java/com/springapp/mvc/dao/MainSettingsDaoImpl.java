package com.springapp.mvc.dao;

import com.springapp.mvc.model.MainSettings;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by oem on 11.10.18.
 */

@Repository
public class MainSettingsDaoImpl implements MainSettingsDao {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<MainSettings> getAllMS() {
        Session session = this.sessionFactory.getCurrentSession();
        List<MainSettings> mainSettingsList = session.createQuery("from MainSettings").list();
        return mainSettingsList;
    }

    @Override
    public void saveMS(MainSettings mainSettings) {

        Session session = this.sessionFactory.getCurrentSession();
        session.save(mainSettings);
    }

    @Override
    public void updateMS(MainSettings mainSettings) {

        Session session = this.sessionFactory.getCurrentSession();
        session.update(mainSettings);
    }

    @Override
    public MainSettings getCurrentMS() {
        List<MainSettings> mainSettingsList = getAllMS();
        MainSettings ms = null;
        if(!mainSettingsList.isEmpty()){
            ms = mainSettingsList.get(mainSettingsList.size()-1);
        }
        return ms;
    }

    @Override
    public MainSettings findMSById(long id) {
        Session session = this.sessionFactory.getCurrentSession();
        MainSettings mainSettings = (MainSettings) session.get(MainSettings.class, new Long(id));
        return mainSettings;
    }
}
