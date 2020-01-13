package com.springapp.mvc.dao;

import com.springapp.mvc.model.ChangesLog;
import com.springapp.mvc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by oem on 22.05.18.
 */
@Repository
public class ChangesLogDaoImpl implements ChangesLogDao {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    @Override
    public void save(ChangesLog changesLog) {
        Session session = this.sessionFactory.getCurrentSession();
        session.save(changesLog);
    }



    public static List<Field> getDifferentFields(Object oldObject, Object newObject){

        List<Field> differentFields = new ArrayList<>();
        Field[] fields = oldObject.getClass().getDeclaredFields();

        try {
            for (Field field : fields) {
                if (field.getAnnotation(Transient.class) == null &&
                        field.getAnnotation(OneToOne.class) == null &&
                        field.getAnnotation(OneToMany.class) == null &&
                        field.getAnnotation(ManyToOne.class) == null &&
                        field.getAnnotation(ManyToMany.class) == null &&
                        !field.getName().equals("password")) {
                    field.setAccessible(true);
                    if (field.get(oldObject) == null && field.get(newObject) == null) {
                    } else {
                        if ((field.get(oldObject) == null && field.get(newObject) != null) ||
                                (field.get(oldObject) != null && field.get(newObject) == null) ||
                                !field.get(oldObject).equals(field.get(newObject))) {

                            differentFields.add(field);
                        }
                    }
                }
                else if(oldObject instanceof User && newObject instanceof User && field.getName().equals("currentAuto"))
                {
                    field.setAccessible(true);
                    if ((((User) oldObject).getCurrentAuto() == null && ((User) newObject).getCurrentAuto() != null) ||
                            (((User) oldObject).getCurrentAuto() != null && ((User) newObject).getCurrentAuto() == null) ||
                            ((User) oldObject).getCurrentAuto().getId() != ((User) newObject).getCurrentAuto().getId()) {
                        differentFields.add(field);
                    }

                }
            }
        } catch (IllegalAccessException e) {
        }
        return differentFields;
    }










    public static String getFieldName(Field field){
        String fieldName = field.getName();
        switch (fieldName){
            case "username": return "логин";
            case "password": return "пароль";
            case "name": return "имя";
            case "patronymic": return "отчество";
            case "surname": return "фамилия";
            case "post": return "должность";
            case "transponder": return "номер транспондера";
            case "eMail": return "eMail";
            case "accountancyType": return "тип отчетности";
            case "isBlocked": return "состояние блокировки";
            case "amortizacia": return "коэффициент амортизации";
            case "currentAuto": return "текущий автомобиль";
            case "brand": return "марка автомобиля";
            case "model":return "модель автомобиля";
            case "number": return "номер";
            case "year": return "год выпуска автомобиля";
            case "engineVolume": return "объем двигателя";
            case "transmission": return "трансмиссия";
            case "bodyType": return "тип кузова";
            case "enginePower": return "мощность двигателя";
            case "climateMachine": return "тип климатической установки";
            case "stsResource": return "путь к файлу СТС";
            case "fuelType": return "тип топлива";
            case "link": return "ссылка на auto.ru";
            case "linkNorm": return "норматив расхода";
            case "osagoResource": return "путь к файлу ОСАГО";
            case "department": return "отдел";
            case "stsNumber": return "номер СТС";

        }
        return fieldName;
    }



}
