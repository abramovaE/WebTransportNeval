package com.springapp.mvc.dao;

import com.springapp.mvc.model.*;
import com.springapp.mvc.vspom.Vspom;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by oem on 25.10.18.
 */
@Repository
public class AutoDaoImpl implements AutoDao {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void saveAuto(Auto auto) {
        Session session = this.sessionFactory.getCurrentSession();
        session.save(auto);
    }

    @Override
    public void updateAuto(Auto auto, User autorizedUser) {

        System.out.println("update auto");
        Session session = this.sessionFactory.getCurrentSession();
        Auto oldAuto = findAutoById(auto.getId());
        List<Field> differentUserFields = ChangesLogDaoImpl.getDifferentFields(oldAuto, auto);
        if(differentUserFields != null && !differentUserFields.isEmpty()){

            List<ChangesLog> changesLogList = new ArrayList<>();
            for(Field field: differentUserFields){
                System.out.println(field.getName());
                ChangesLog changesLog = new ChangesLog();
                try {
                    Class autoClass = auto.getClass();
                    Field autoField = autoClass.getDeclaredField(field.getName());
                    autoField.setAccessible(true);

                    Object autoFieldValue = field.get(auto);
                    Object oldAutFieldValue = field.get(oldAuto);

                    if(autoFieldValue == null){
                        autoFieldValue = new String("");
                    }

                    if(oldAutFieldValue == null){
                        oldAutFieldValue = new String("");
                    }

                    String subject = auto.getBrand() +" " + ChangesLogDaoImpl.getFieldName(field);


                    changesLog.setNewData(autoFieldValue.toString());
                    changesLog.setOldData(oldAutFieldValue.toString());
                    LocalDateTime localDateTime= LocalDateTime.now();
                    changesLog.setDate(localDateTime.format(DateTimeFormatter.ofPattern("d MMM uuuu HH:mm:ss")).toString());

                    changesLog.setSubject(subject);
                    changesLog.setUser(auto.getUser());
                    changesLog.setAuto(auto);
                    changesLog.setWhoChanged(autorizedUser);
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                changesLogList.add(changesLog);
                session.save(changesLog);
            }
        }
        session.clear();








        session.update(auto);
    }


    @Override
    public void updateAuto(Auto auto) {
        Session session = this.sessionFactory.getCurrentSession();



        session.update(auto);
    }

    @Override
    public List<Auto> getAllAutos() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Auto> allAutos = session.createCriteria(Auto.class).list();
        return allAutos;
    }

    @Override
    public List<Auto> getAllAutosByUser(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        List<Auto> autos = session.createCriteria(Auto.class).add(Restrictions.eq("user", user)).list();
        return autos;
    }

    @Override
    public Auto findAutoById(long autoId) {
        Session session = this.sessionFactory.getCurrentSession();
        Auto auto =(Auto) session.get(Auto.class, new Long(autoId));
        return auto;

    }

    @Override
    public boolean hasClosedReports(Auto auto) {
        Session session = this.sessionFactory.getCurrentSession();
        List<Report> autoReports = session.createCriteria(Report.class).add(Restrictions.eq("auto", auto)).add(Restrictions.eq("isClosed", true)).list();
        return autoReports.size() > 0;
    }


    @Override
    public List<Auto> findAutoByParam(String brand, String number,
                                Integer autoYear, String bodyType, Double engineVolume, Integer enginePower, String transmission,
                                String fuelType, String climateMachine, String link, Double linkNorm, User user) {
        Session session = this.sessionFactory.getCurrentSession();


        List<Auto> autos = session.createCriteria(Auto.class).add(Restrictions.eq("brand", brand)).
                add(Restrictions.eq("number", number)).add(Restrictions.eq("year", autoYear)).
                add(Restrictions.eq("bodyType", bodyType)).add(Restrictions.eq("engineVolume", engineVolume))
                .add(Restrictions.eq("enginePower", enginePower)).add(Restrictions.eq("transmission", transmission))
                .add(Restrictions.eq("fuelType", fuelType)).add(Restrictions.eq("climateMachine", climateMachine))
                .add(Restrictions.eq("link", link)).add(Restrictions.eq("linkNorm", linkNorm)).add(Restrictions.eq("user", user)).list();

        return autos;
    }
}
