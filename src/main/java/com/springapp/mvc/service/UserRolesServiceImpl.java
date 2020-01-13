package com.springapp.mvc.service;

import com.springapp.mvc.dao.UserRolesDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Created by kot on 31.08.17.
 */
@Service
public class UserRolesServiceImpl implements UserRolesService {

    private UserRolesDao userRolesDao;

    public void setUserRolesDao(UserRolesDao userRolesDao) {
        this.userRolesDao = userRolesDao;
    }

    @Override
    @Transactional
    public void deleteUserRoles(long id) {
        this.userRolesDao.deleteUserRoles(id);
    }


}
