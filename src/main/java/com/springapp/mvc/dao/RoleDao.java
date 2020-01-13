package com.springapp.mvc.dao;

import com.springapp.mvc.model.Role;
import com.springapp.mvc.model.User;


import java.util.List;


/**
 * Created by kot on 04.08.17.
 */
public interface RoleDao {

    Role findByIdRole(long id);
    Role findByRoleName(String name);
    void updateRole(Role role);


}
