package com.springapp.mvc.dao;

import com.springapp.mvc.model.AdressesCoordinates;
import com.springapp.mvc.model.MainSettings;
import com.springapp.mvc.model.Point;
import com.springapp.mvc.model.User;
import com.springapp.mvc.vspom.Constants;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by kot on 10.07.17.
 */

@Repository
public class AdressesCoordinatesDaoImpl implements AdressesCoordinatesDao {

    private SessionFactory sessionFactory;
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }




    @Override
    public List<AdressesCoordinates> getAllAdresses() {
        Session session = sessionFactory.getCurrentSession();
        List<AdressesCoordinates> allAdresses = session.createCriteria(AdressesCoordinates.class).list();
        return allAdresses;
    }

    @Override
    public List<AdressesCoordinates> getAdressesForAutoReports() {
        Session session = sessionFactory.getCurrentSession();
        List<AdressesCoordinates> allAdresses = session.createCriteria(AdressesCoordinates.class)
                .add(Restrictions.eq("usedForAutoReports", true)).list();
        return allAdresses;
    }

    @Override
    public void addNewAdressCoordinates(AdressesCoordinates adressesCoordinates) {
        Session session = this.sessionFactory.getCurrentSession();
        session.save(adressesCoordinates);
    }

    @Override
    public void updateAdressCoordinates(AdressesCoordinates adressesCoordinates) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(adressesCoordinates);
    }

    @Override
    public AdressesCoordinates findByIdAdressCoordinates(long id) {
        Session session = sessionFactory.getCurrentSession();
        AdressesCoordinates adressesCoordinates = (AdressesCoordinates) session.get(AdressesCoordinates.class, new Long(id));
        return adressesCoordinates;
    }

    @Override
    public AdressesCoordinates findAdressesCoordinatesByAdress(String adress) {
        Session session = this.sessionFactory.getCurrentSession();
        AdressesCoordinates adressesCoordinates = (AdressesCoordinates) session.createCriteria(AdressesCoordinates.class)
                .add(Restrictions.eq("address", adress)).uniqueResult();
        return adressesCoordinates;
    }


    @Override
    public List<AdressesCoordinates> getAllAdresssesWithOfficePriority(MainSettings currentMS) {
        Session session = sessionFactory.getCurrentSession();
        List<AdressesCoordinates> list = new ArrayList<>();
        list.add(currentMS.getTechOffice());
        list.addAll(session.createCriteria(AdressesCoordinates.class).add(Restrictions.ne("id", currentMS.getTechOffice().getId())).list());
        return list;
    }


}
