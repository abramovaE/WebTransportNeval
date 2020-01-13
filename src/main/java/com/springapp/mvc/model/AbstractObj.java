package com.springapp.mvc.model;

import java.time.LocalDate;

/**
 * Created by oem on 13.11.18.
 */
public abstract class AbstractObj extends Model implements CommonMethods {

    private final static String RED = "red";
    private final static String ORANGE = "orange";
    private final static String GREEN = "green";
    private final static String GREY = "grey";

    public String getClassColor(){
        String date = getDateForColor();
            if(date != null && !date.isEmpty()){
                LocalDate nowDateTime = LocalDate.now();
                String[] y_m_d = date.split("-");
                LocalDate vuLocalDate = LocalDate.of(Integer.parseInt(y_m_d[0]), Integer.parseInt(y_m_d[1]), Integer.parseInt(y_m_d[2]));
                java.time.Period period = nowDateTime.until(vuLocalDate);
                if(period.getYears() == 0 && period.getMonths() == 0){
                    return RED;
                }
                else if(period.getYears() == 0 && period.getMonths() < 2){
                    return ORANGE;
                }
                else {
                    return GREEN;
                }
            }
            return GREY;
    }

    public AbstractObj() {
        super();
    }
}
