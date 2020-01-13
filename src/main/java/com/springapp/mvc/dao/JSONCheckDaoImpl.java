package com.springapp.mvc.dao;

import com.springapp.mvc.model.checks.JSONCheck;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by oem on 24.12.18.
 */

@Repository
public class JSONCheckDaoImpl implements JSONCheckDao {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<JSONCheck> getAllJSONChecks() {
        Session session = this.sessionFactory.getCurrentSession();
        List<JSONCheck> jsonChecks = session.createQuery("from JSONCheck").list();
        return jsonChecks;
    }
}
