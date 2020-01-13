package com.springapp.mvc.dao;

import com.springapp.mvc.model.Check;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

/**
 * Created by oem on 24.11.18.
 */

@Repository
public class CheckDaoImpl implements  CheckDao{


    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void saveCheck(Check check) {

        Session session = this.sessionFactory.getCurrentSession();
        session.save(check);

    }
}
