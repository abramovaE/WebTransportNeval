package com.springapp.mvc.dao;

import com.springapp.mvc.model.CostByZone;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

/**
 * Created by kot on 14.09.17.
 */

@Repository
public class CostByZoneDaoImpl implements CostByZoneDao {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<CostByZone> getAllCosts() {
        Session session = this.sessionFactory.getCurrentSession();

        List<CostByZone> list = session.createQuery("from CostByZone").list();
        return list;
    }

    @Override
    public void addNewZone(CostByZone costByZone) {
        Session session = this.sessionFactory.getCurrentSession();
        session.save(costByZone);
    }

    @Override
    public CostByZone findZoneById(long costByZoneId) {
        Session session = this.sessionFactory.getCurrentSession();
        CostByZone costByZone = (CostByZone) session.get(CostByZone.class, new Long(costByZoneId));
        return costByZone;
    }

    @Override
    public void updateCostByZome(CostByZone costByZone) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(costByZone);
    }

    @Override
    public Integer findCostByZoneByNumberOfZone(Integer numberOfZone) {
        Session session = this.sessionFactory.getCurrentSession();
        CostByZone costByZone = new CostByZone();
        costByZone.setNumberZone(numberOfZone);
        List<CostByZone> list = session.createCriteria(CostByZone.class).add(Example.create(costByZone)).list();

        if(!list.isEmpty()){
            return list.get(0).getCostByZone();
        }

        else return null;
    }
}
