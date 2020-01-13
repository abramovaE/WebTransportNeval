package com.springapp.mvc.dao;

import com.springapp.mvc.model.checks.Receipt;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

/**
 * Created by oem on 28.11.18.
 */
@Repository
public class ReceiptDaoImpl implements ReceiptDao {
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void saveReceipt(Receipt receipt) {
        Session session = this.sessionFactory.getCurrentSession();
        session.save(receipt);
    }
}
