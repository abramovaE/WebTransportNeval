package com.springapp.mvc.service;


import com.springapp.mvc.dao.RoleDao;
import com.springapp.mvc.dao.UserDao;
import com.springapp.mvc.model.*;
import com.springapp.mvc.enums.RoleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by kot on 22.03.17.
 */
@Service
public class UserServiceImpl implements UserService {



    private UserDao userDao;

    private RoleDao roleDao;

    @Autowired
    private UserDetailsService userDetailsService;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    @Transactional
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    @Transactional
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Set<Role> roles = new HashSet<>();
        roles.add(roleDao.findByIdRole(1));
        user.setRoles(roles);
        userDao.save(user);
    }

    @Override
    @Transactional
    public List<User> getAllUsers() {
        return this.userDao.getAllUsers();
    }


    @Override
    @Transactional
    public List<User> getAllDrivers() {
        return this.userDao.getAllDrivers();
    }

    @Override
    @Transactional
    public List<User> getAllNotDrivers() {
        return  this.userDao.getAllNotDrivers();
    }

    @Override
    @Transactional
    public List<User> getAllUsers(String param) {
        return this.userDao.getAllUsers(param);
    }


    @Override
    @Transactional
    public List<User> getAllDrivers(String param) {
        return this.userDao.getAllDrivers(param);
    }


    @Override
    @Transactional
    public List<User> getAllNotDrivers(String param) {
        return this.userDao.getAllNotDrivers(param);
    }



    @Override
    @Transactional
    public void delete(long id) {
        this.userDao.delete(id);
    }




    @Override
    @Transactional
    public void updateUser(User user, User autorizedUser) {


        UserDetails userDetails = this.userDetailsService.loadUserByUsername(user.getLogin());
        Set<Role> roles = new HashSet<>();

        for(GrantedAuthority grantedAuthority: userDetails.getAuthorities()){
            Role role = roleDao.findByRoleName(grantedAuthority.getAuthority());
            role.setRolename(grantedAuthority.getAuthority());
            roles.add(role);
        }

        user.setRoles(roles);

        this.userDao.updateUser(user, autorizedUser);
    }


    @Override
    @Transactional
    public void delUser(long id) {
        this.userDao.delUser(id);
    }

    @Override
    @Transactional
    public User findByIdUser(long id) {
        return this.userDao.findByIdUser(id);
    }

    @Override
    @Transactional
    public String getfullUserName(User user) {
        return userDao.getfullUserName(user);
    }


    @Override
    @Transactional
    public void changePassword(User user, User autorizedUser) {
        User oldUser = findByIdUser(user.getId());
        oldUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        updateUser(oldUser, autorizedUser);
    }


    @Override
    @Transactional
    public void blockUser(User user, User autorizedUser) {
        this.userDao.blockUser(user, autorizedUser);
    }

    @Override
    @Transactional
    public void setBuhgalteria(User user, User autorizedUser) {
        this.userDao.setBuhgalteria(user, autorizedUser);
    }


    @Override
    @Transactional
    public List<Report> allReportsByUserRevSort(User user) {
        return this.userDao.allReportsByUserRevSort(user);
    }

    @Override
    @Transactional
    public List<Auto> allAutosByUser(User user) {
        return this.userDao.allAutosByUser(user);
    }

    @Override
    @Transactional
    public Report getReportByUserByPeriod(User user, String period) {
        return this.userDao.getReportByUserByPeriod(user, period);
    }

    @Override
    @Transactional
    public String getShortFullName(User user) {
        return this.userDao.getShortFullName(user);
    }


    @Override
    @Transactional
    public Map<User, List<Report>> getMapForMobile(int year) {
        return this.userDao.getMapForMobile(year);
    }

    @Override
    @Transactional
    public List<ChangesLog> allChangesLogByUser(User user) {
        return this.userDao.allChangesLogByUser(user);
    }

    @Override
    @Transactional
    public Map<RoleEnum, Boolean> getRolesMap(User user) {
        return this.userDao.getRolesMap(user);
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        this.userDao.updateUser(user);
    }

    @Override
    @Transactional
    public void saveChangesLog(User user, User autorizeduser) {
        this.userDao.saveChangesLog(user,autorizeduser);
    }

    @Override
    @Transactional
    public void fireUser(User user, User autorizedUser) {
     this.userDao.fireUser(user, autorizedUser);
    }
}