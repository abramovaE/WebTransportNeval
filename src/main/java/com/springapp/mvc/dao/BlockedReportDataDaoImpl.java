package com.springapp.mvc.dao;

import com.springapp.mvc.model.BlockedReportData;
import com.springapp.mvc.model.Report;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by oem on 24.05.18.
 */

@Repository
public class BlockedReportDataDaoImpl implements BlockedReportDataDao {




    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(BlockedReportData blockedReportData) {
        Session session = this.sessionFactory.getCurrentSession();
        session.save(blockedReportData);
    }

    @Override
    public void update(BlockedReportData blockedReportData) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(blockedReportData);
    }

    @Override
    public List<BlockedReportData> getAllBRD() {
        Session session = this.sessionFactory.getCurrentSession();
        List<BlockedReportData> blockedReportDatas = session.createCriteria(BlockedReportData.class).list();
        return blockedReportDatas;
    }

    @Override
    public void deleteBRD(long id) {
        Session session = this.sessionFactory.getCurrentSession();
        BlockedReportData brd = (BlockedReportData) session.get(BlockedReportData.class, new Long(id));
        if(brd != null){
            session.delete(brd);
        }
    }
}


