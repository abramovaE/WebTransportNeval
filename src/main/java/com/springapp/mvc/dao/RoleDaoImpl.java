package com.springapp.mvc.dao;

import com.springapp.mvc.model.Role;
import com.springapp.mvc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by kot on 04.08.17.
 */

@Repository
public class RoleDaoImpl implements RoleDao {

    private SessionFactory sessionFactory;
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Role findByIdRole(long id) {
        Session session = sessionFactory.getCurrentSession();
        Role role = (Role) session.get(Role.class, new Long(id));
        return role;
    }

    @Override
    public Role findByRoleName(String name) {
        Session session = this.sessionFactory.getCurrentSession();
        Role role = new Role();
        role.setRolename(name);
        return (Role) session.createCriteria(Role.class).add(Example.create(role)).uniqueResult();
    }

    @Override
    public void updateRole(Role role) {
        Session session = sessionFactory.getCurrentSession();
        session.update(role);
    }


}
