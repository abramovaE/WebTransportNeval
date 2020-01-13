package com.springapp.mvc.dao;

import com.springapp.mvc.model.Department;
import com.springapp.mvc.model.Report;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by oem on 23.05.19.
 */

@Repository
public class DepDaoImpl implements DepDao {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void saveDep(Department department) {
        Session session = this.sessionFactory.getCurrentSession();
        session.save(department);
    }

    @Override
    public List<Department> getAllDepts() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Department> depts = session.createQuery("from Department").list();
        return depts;
    }

    @Override
    public void updateDep(Department department) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(department);
    }

    @Override
    public Department findDepById(long depId) {
        Session session = sessionFactory.getCurrentSession();
        Department department = (Department) session.get(Department.class, new Long(depId));
        return department;
    }

    @Override
    public void deleteDep(Department department) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(department);
    }
}
