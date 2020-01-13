package com.springapp.mvc.vspom;

import com.springapp.mvc.model.*;

import javax.persistence.*;
import java.io.*;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Created by oem on 12.12.17.
 */
public class Vspom {



    public static void copyFile(File source, File dest) throws IOException {
        Files.copy(source.toPath(), dest.toPath());
    }


    public static List<Day> getWorkingDaysOfMonth(YearMonth yearMonth){
        List<Day> workingDaysOfMonth = new ArrayList<>();
        for(int i = 1; i < yearMonth.lengthOfMonth() + 1; i++){
            Day day = new Day();
            LocalDate localDate = LocalDate.of(yearMonth.getYear(), yearMonth.getMonthValue(), i);

            if(!localDate.getDayOfWeek().equals(DayOfWeek.SATURDAY) && !localDate.getDayOfWeek().equals(DayOfWeek.SUNDAY)){
                day.setDayNumber(i);
                workingDaysOfMonth.add(day);
            }
        }
        return workingDaysOfMonth;
    }



    public static AdressesCoordinates findRandomAdressCoordinatesFromList(List<AdressesCoordinates> adressesCoordinatesList){
        Random random = new Random();
        int randomAdress = random.nextInt(adressesCoordinatesList.size());
        AdressesCoordinates adressesCoordinates = adressesCoordinatesList.get(randomAdress);
        return adressesCoordinates;
    }



    public static Report createCanvasAutoReport(List<AdressesCoordinates> allAdressesCoordinates, MainSettings mainSettings,
                                                int pointsCount, boolean isDirector, int monthNumber, int year){


        Report report = new Report();
        YearMonth nowYearMonth;
        YearMonth prevYearMonth;

        if(monthNumber == 0 && year == 0){
            nowYearMonth = YearMonth.now();
            prevYearMonth = nowYearMonth.minusMonths(1);
        }

        else {
            prevYearMonth = YearMonth.of(year, monthNumber);
        }

        report.setYear(prevYearMonth.getYear());
        report.setMonthNumber(prevYearMonth.getMonthValue());
        AdressesCoordinates officialOffice = mainSettings.getOfficialOffice();
        AdressesCoordinates techOffice = mainSettings.getTechOffice();

        List<Day> days = Vspom.getWorkingDaysOfMonth(prevYearMonth);
        Random random = new Random();

        if(isDirector){
//        удаляем случайное число дней (до пяти дней в месяце)
            int countNoWorkingDays = random.nextInt(4) + 1;

//        индексы удаляемых дней для days
            List<Integer> noDriving = new ArrayList<>();
            for(int i = 0; i < countNoWorkingDays; i++){
                int r = random.nextInt(days.size());
                if(!noDriving.contains(r)) {
                    noDriving.add(r);
                }
            }

//        удаляемые из days дни
            List<Day> removeDays = new ArrayList<>();
            for(int i = 0; i < noDriving.size(); i++){
                removeDays.add(days.get(noDriving.get(i)));
            }
            days.removeAll(removeDays);
        }

        for(Day day:days){
            List<Point> points = day.getPoints();

            while (true){
                        if(points == null || points.isEmpty()){
                            points = new LinkedList<>();
                            Point firstPoint = new Point();
                            AdressesCoordinates arr = Vspom.findRandomAdressCoordinatesFromList(allAdressesCoordinates);
                            AdressesCoordinates office;
                            if(isDirector){
                                office = officialOffice;
                            }
                            else {
                                office = techOffice;
                            }
                            firstPoint.setDeparture(office);
                            while (arr.equals(office) || arr.getAddress() == null || arr.getAddress().isEmpty()) {
                                arr = Vspom.findRandomAdressCoordinatesFromList(allAdressesCoordinates);
                            }
                            firstPoint.setArrival(arr);
                            firstPoint.setDay(day);
                            firstPoint.setPosition(0);
                            points.add(firstPoint);

                        }

                        else {
                            Point nextPoint = new Point();
                            AdressesCoordinates dep = points.get(points.size() - 1).getArrival();
                            nextPoint.setDeparture(dep);
                            AdressesCoordinates arr = Vspom.findRandomAdressCoordinatesFromList(allAdressesCoordinates);

                            if(isDirector){
                                while (arr.equals(officialOffice) || arr.getAddress() == null || arr.getAddress().isEmpty() || arr == dep) {
                                    arr = Vspom.findRandomAdressCoordinatesFromList(allAdressesCoordinates);
                                }
                            }

                            else {
                                while (arr.getAddress() == null || arr.getAddress().isEmpty() || arr == dep) {
                                    arr = Vspom.findRandomAdressCoordinatesFromList(allAdressesCoordinates);
                                }
                            }

                            nextPoint.setPosition(points.size());
                            nextPoint.setArrival(arr);
                            nextPoint.setDay(day);
                            points.add(nextPoint);

                        }

                    if (points.size() > pointsCount){
                        if(isDirector){
                            Point lastPoint = points.get(pointsCount);
                            lastPoint.setArrival(officialOffice);
                            break;
                        }
                        else {
                            break;
                        }
                    }



                }
                day.setPoints(points);
                day.setReport(report);

            }

            if(isDirector){
//        устанавливаем для каждого дня поездку на Просвещения
                for(Day day: days){
                    List<Point> pointsOfDay = day.getPoints();
                    int max = pointsOfDay.size() - 1;
                    int r = random.nextInt(max);
                    Point randomPoint = pointsOfDay.get(r);
                    randomPoint.setArrival(techOffice);
                    Point nextByRandomPoint = pointsOfDay.get(r + 1);
                    nextByRandomPoint.setDeparture(techOffice);
                }
            }
        report.setDays(days);
        return report;
    }

}


