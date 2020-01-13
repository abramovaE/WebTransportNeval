package com.springapp.mvc.dao;


import com.springapp.mvc.model.*;

import com.springapp.mvc.vspom.DateVspom;
import com.springapp.mvc.enums.RoleEnum;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Created by kot on 22.03.17.
 */
@Repository
public class UserDaoImpl implements UserDao {

    private SessionFactory sessionFactory;
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public User findByUsername(String s) {
        Session session = this.sessionFactory.getCurrentSession();
        return  (User) session.createCriteria(User.class).add(Restrictions.eq("login", s)).uniqueResult();
    }

    @Override
    public void save(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        session.save(user);
    }




    @Override
    public List<User> getAllUsers() {
        Session session=this.sessionFactory.getCurrentSession();
        List<User> list = (List<User>) session.createCriteria(User.class).
                        addOrder(Order.asc("fired")).
                        addOrder(Order.asc("surname")).
                        addOrder(Order.asc("name")).
                        addOrder(Order.asc("patronymic")).
                        list();
        return list;
    }



    @Override
    public List<User> getAllUsers(String param) {
        Session session=this.sessionFactory.getCurrentSession();
        List<User> list = (List<User>) session.createCriteria(User.class).addOrder(Order.asc(param)).list();
        return list;
    }



    @Override
    public void delUser(long id) {
        Session session = this.sessionFactory.getCurrentSession();
        User user = (User) session.get(User.class, new Long(id));
        if(user != null && user.getReports().isEmpty()){
            session.delete(user);
        }
    }



    @Override
    public List<User> getAllDrivers() {
        Session session=this.sessionFactory.getCurrentSession();
        List<User> list = (List<User>) session.createCriteria(User.class)
                .add(Restrictions.isNotNull("currentAuto")).
                        addOrder(Order.asc("fired")).
                        addOrder(Order.asc("surname")).
                        addOrder(Order.asc("name")).
                        addOrder(Order.asc("patronymic")).
                        list();
        return list;
    }

    @Override
    public List<User> getAllDrivers(String param) {
        Session session=this.sessionFactory.getCurrentSession();

        List<User> list = null;
        List<String> autoFields = new ArrayList<>();
        List<String> userFields = new ArrayList<>();
        for(Field field: Auto.class.getDeclaredFields()){
            autoFields.add(field.getName());
        }

        for(Field field: User.class.getDeclaredFields()){
            userFields.add(field.getName());
        }



        if(autoFields.contains(param)){
            list = (List<User>) session.createCriteria(User.class)
                    .add(Restrictions.isNotNull("currentAuto"))
                    .createAlias("currentAuto", "currentAuto")
                    .addOrder(Order.asc("currentAuto."+param))
                    .list();
        }

        else if(userFields.contains(param)){
            list = (List<User>) session.createCriteria(User.class)
                    .add(Restrictions.isNotNull("currentAuto"))
                    .addOrder(Order.asc(param))
                    .list();
        }

        return list;
    }








    @Override
    public List<User> getAllNotDrivers() {
        Session session=this.sessionFactory.getCurrentSession();
        List<User> list = (List<User>) session.createCriteria(User.class)
                .add(Restrictions.isNull("currentAuto")).
                        addOrder(Order.asc("fired")).
                        addOrder(Order.asc("surname")).
                        addOrder(Order.asc("name")).
                        addOrder(Order.asc("patronymic")).
                            list();
        return list;
    }

    @Override
    public List<User> getAllNotDrivers(String param) {
        Session session=this.sessionFactory.getCurrentSession();
        List<User> list = (List<User>) session.createCriteria(User.class)
                .add(Restrictions.isNull("currentAuto"))
                 .addOrder(Order.asc(param))
                .list();
        return list;
    }




    @Override
    public void delete(long id) {
        Session session = this.sessionFactory.getCurrentSession();
        User u = (User) session.load(User.class, new Long(id));
        if(u!=null) {
            session.delete(u);
        }
    }

    @Override
    public void updateUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.update(user);
    }

        @Override
    public void updateUser(User user, User autorizedUser) {
//            saveChangesLog(user, autorizedUser);
        Session session = sessionFactory.getCurrentSession();
        session.update(user);
    }

    @Override
    public User findByIdUser(long id) {
        Session session = sessionFactory.getCurrentSession();
        User user = (User) session.get(User.class, new Long(id));
//        session.close();
        return user;
    }

    @Override
    public String getfullUserName(User user) {
        String name = user.getLogin();
        if (user.getSurname() != null && !user.getSurname().isEmpty() && user.getName() != null && !user.getName().isEmpty() && user.getPatronymic() != null && !user.getPatronymic().isEmpty()) {
            name = user.getSurname() + " " + user.getName() + " " + user.getPatronymic();
        }
        return name;
    }



    public Map<RoleEnum, Boolean> getRolesMap(User user){
        Map<RoleEnum, Boolean> rolesMap = new HashMap<>();
        Set<Role> userRoles = user.getRoles();
        Set<String> rolesNames = new HashSet<>();
        for(Role role: userRoles){
            rolesNames.add(role.getRolename());
        }
        for(RoleEnum roleEnum: RoleEnum.values()){
            rolesMap.put(roleEnum, rolesNames.contains(String.valueOf(roleEnum)));
        }
        return rolesMap;
    }


    @Override
    public void blockUser(User user, User autorizedUser) {
        user.setBlocked(!user.getBlocked());
        updateUser(user, autorizedUser);
    }


    @Override
    public void fireUser(User user, User autorizedUser) {
        user.setFired(true);
        updateUser(user, autorizedUser);
    }

    @Override
    public void setBuhgalteria(User user, User autorizedUser) {
        user.setBuhgalteria(!user.getBuhgalteria());
        updateUser(user, autorizedUser);
    }



    @Override
    public List<Report> allReportsByUserRevSort(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        List<Report> reports = session.createCriteria(Report.class).add(Restrictions.eq("user", user))
                .addOrder(Order.asc("isClosed"))
                .addOrder(Order.desc("year"))
                .addOrder(Order.desc("monthNumber")).list();
        return reports;
    }

    @Override
    public List<Auto> allAutosByUser(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        List<Auto> autos = session.createCriteria(Auto.class).add(Restrictions.eq("user", user)).list();
        return autos;
    }

    @Override
    public List<ChangesLog> allChangesLogByUser(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        List<ChangesLog> changesLogList = session.createCriteria(ChangesLog.class).add(Restrictions.eq("user", user)).list();
        return changesLogList;
    }





    @Override
    public Report getReportByUserByPeriod(User user, String period) {
        Session session = sessionFactory.getCurrentSession();
        YearMonth yearMonth = DateVspom.getYearMonthFromPeriod(period);
        return (Report) session.createCriteria(Report.class)
                .add(Restrictions.eq("user", user))
                .add(Restrictions.eq("year", yearMonth.getYear()))
                .add(Restrictions.eq("monthNumber", yearMonth.getMonthValue()))
                .uniqueResult();
    }

    @Override
    public String getShortFullName(User user) {
        StringBuilder fullName = new StringBuilder();
        String surname = user.getSurname();
        String name = user.getName();
        String patronymic = user.getPatronymic();
        if(surname != null && !surname.isEmpty()){
            fullName.append(surname);
        }
        if(name != null && !name.isEmpty()) {
            fullName.append(" " + name.substring(0, 1) + ".");
        }
        if(patronymic != null && !patronymic.isEmpty()){
            fullName.append(" " + patronymic.substring(0, 1) + ".");
        }
        return fullName.toString();
    }



    @Override
    public Map<User, List<Report>> getMapForMobile(int year){
        List<User> users = getAllDrivers();
        Set<String> months = DateVspom.getAllMonths();

        Map<User, List<Report>> map = new TreeMap<>(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return o1.getShortFullName().compareTo(o2.getShortFullName());
            }
        });

        for(User user: users){
            for(String month : months){
                List<Report> listOfUserReports;
                if(!map.containsKey(user)){
                    listOfUserReports = new LinkedList<>();
                }
                else {
                    listOfUserReports = map.get(user);
                }
                Report report = getReportByUserByPeriod(user, month + " " + year);
                listOfUserReports.add(report);
                map.put(user, listOfUserReports);
            }
        }
        return map;
    }


    @Override
    public void saveChangesLog(User user, User autorizedUser) {
        User oldUser =  findByIdUser(user.getId());
        Field[] fields = user.getClass().getDeclaredFields();
        Session session = sessionFactory.getCurrentSession();
        try {
            for (Field field : fields) {

                if (field.getAnnotation(Transient.class) == null &&
                        field.getAnnotation(OneToOne.class) == null &&
                        field.getAnnotation(OneToMany.class) == null &&
                        field.getAnnotation(ManyToOne.class) == null &&
                        field.getAnnotation(ManyToMany.class) == null &&
                        !field.getName().equals("password") ||
                        field.getName().equals("currentAuto")) {
                    field.setAccessible(true);

                    Object oldUserFieldValue = field.get(oldUser);
                    Object userFieldValue = field.get(user);

                    if(userFieldValue == null){
                        userFieldValue = new String("");
                    }

                    if(oldUserFieldValue == null){
                        oldUserFieldValue = new String("");
                    }

                    if (userFieldValue instanceof Auto) {
                        Auto newAuto = (Auto) session.get(Auto.class, new Long(((Auto) userFieldValue).getId()));
                        if (newAuto != null)
                            userFieldValue = newAuto.getBrand();
                    }

                    if (oldUserFieldValue instanceof Auto) {
                        oldUserFieldValue = ((Auto) oldUserFieldValue).getBrand();
                    }

                    if (userFieldValue instanceof Department) {
                        userFieldValue = ((Department) userFieldValue).getName();
                    }

                    if (oldUserFieldValue instanceof Department) {
                        oldUserFieldValue = ((Department) oldUserFieldValue).getName();
                    }

                    if (!oldUserFieldValue.equals(userFieldValue)) {


                        if(!field.getName().equals("currentAuto") || (field.getName().equals("currentAuto") && oldUser.getCurrentAuto() == null || oldUser.getCurrentAuto().getId() != user.getCurrentAuto().getId())) {
                            ChangesLog changesLog = new ChangesLog();
                            changesLog.setNewData(userFieldValue.toString());
                            changesLog.setOldData(oldUserFieldValue.toString());
                            LocalDateTime localDateTime = LocalDateTime.now();
                            changesLog.setDate(localDateTime.format(DateTimeFormatter.ofPattern("d MMM uuuu HH:mm:ss")).toString());
                            String subject = ChangesLogDaoImpl.getFieldName(field);
                            changesLog.setSubject(subject);
                            changesLog.setUser(user);
                            changesLog.setWhoChanged(autorizedUser);
                            session.save(changesLog);
                        }
                    }
                }
            }
        } catch (IllegalAccessException e) {
        }
    }
}
