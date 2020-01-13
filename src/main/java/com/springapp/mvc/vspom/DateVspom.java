package com.springapp.mvc.vspom;

import com.springapp.mvc.model.User;

import java.time.YearMonth;
import java.util.*;

/**
 * Created by kot on 06.10.17.
 */
public class DateVspom {


    private static final Map<String, Integer> months = new LinkedHashMap<>();
    private static final Map<Integer, String> numberMonths = new LinkedHashMap<>();

    static {
        months.put("Январь", 1);
        months.put("Февраль", 2);
        months.put("Март", 3);
        months.put("Апрель", 4);
        months.put("Май", 5);
        months.put("Июнь", 6);
        months.put("Июль" ,7);
        months.put("Август", 8);
        months.put("Сентябрь", 9);
        months.put("Октябрь", 10);
        months.put("Ноябрь", 11);
        months.put("Декабрь", 12);

        numberMonths.put(1, "Январь");
        numberMonths.put(2, "Февраль");
        numberMonths.put(3, "Март");
        numberMonths.put(4, "Апрель");
        numberMonths.put(5, "Май");
        numberMonths.put(6, "Июнь");
        numberMonths.put(7, "Июль");
        numberMonths.put(8, "Август");
        numberMonths.put(9, "Сентябрь");
        numberMonths.put(10, "Октябрь");
        numberMonths.put(11, "Ноябрь");
        numberMonths.put(12, "Декабрь");
    }


    public static Map<String, Integer> getMonths() {
        return months;
    }

    public static int getNumberOfMonth(String month){
        return months.get(month);
    }


    public static YearMonth getYearMonthFromPeriod (String period){
        System.out.println(period);
        String[] monthYear = period.split(" ");
        int year = Integer.parseInt(monthYear[1]);
        int month = getNumberOfMonth(monthYear[0].trim());
        return YearMonth.of(year, month);
    }


    public static String getPeriodFromYearMonth(YearMonth yearMonth){
        int year = yearMonth.getYear();
        int month = yearMonth.getMonthValue();
        String textMonth = getMonthFromInt(month);
        return textMonth + " " + year;
    }


    public static String getMonthFromInt(int number){
        return numberMonths.get(number);
    }


    public static Set<String> getAllMonths(){
        return months.keySet();
    }



    public static Set<String> getAllMonthWithMonthPriority(int monthNumber){

        if(monthNumber == 0){
            monthNumber = YearMonth.now().getMonthValue();
        }

        LinkedHashSet<String> result = new LinkedHashSet<>();
        for(int i = monthNumber; i < 13; i++){
            result.add(getMonthFromInt(i));
        }
        for(int i = 1; i < monthNumber; i++){
            result.add(getMonthFromInt(i));
        }
        return result;
    }
}
