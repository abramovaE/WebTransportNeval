package com.springapp.mvc.dao;

import com.springapp.mvc.model.*;
import com.springapp.mvc.enums.RoleEnum;

import java.util.List;
import java.util.Map;

/**
 * Created by kot on 22.03.17.
 */
public interface UserDao {
    User findByUsername(String username);
    void save(User user);
    void updateUser(User user, User autorizedUser);
    void updateUser(User user);
    void delUser(long id);
    User findByIdUser(long id);


    List<User> getAllUsers();
    List<User> getAllUsers(String param);

    List<User> getAllDrivers();
    List<User> getAllDrivers(String param);

    List<User> getAllNotDrivers();
    List<User> getAllNotDrivers(String param);



    void delete(long id);
    String getfullUserName(User user);



    Map<RoleEnum, Boolean> getRolesMap(User user);


    void blockUser(User user, User autorizedUser);
    void fireUser(User user, User autorizedUser);
    void setBuhgalteria(User user, User autorizedUser);


    List<Report> allReportsByUserRevSort(User user);
    List<Auto> allAutosByUser(User user);

    List<ChangesLog> allChangesLogByUser(User user);





    Report getReportByUserByPeriod(User user, String period);

    String getShortFullName(User user);



    Map<User, List<Report>> getMapForMobile(int year);

    void saveChangesLog(User user, User autorizeduser);




}
