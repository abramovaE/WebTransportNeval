package com.springapp.mvc.dao;

import com.springapp.mvc.model.UserRoles;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

/**
 * Created by kot on 31.08.17.
 */
@Repository
public class UserRolesDaoImpl implements UserRolesDao{

    private SessionFactory sessionFactory;
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void deleteUserRoles(long id) {
        Session session = this.sessionFactory.getCurrentSession();

        UserRoles userRoles = (UserRoles) session.load(UserRoles.class, new Long(id));

        if(userRoles != null){
            session.delete(userRoles);
        }
    }
}
