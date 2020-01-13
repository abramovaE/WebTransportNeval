package com.springapp.mvc.vspom;

import com.springapp.mvc.model.MainSettings;

/**
 * Created by oem on 20.03.18.
 */
public class Constants {


    public static final String MAIN_PATH = "/home/kotofeya/web/WT";

    private static final String RESULT_REPORT_FILENAME = "report.xlsx";
    private static final String addressFrom = "raspprojectacc@gmail.com";
    private static final String passwordFrom = "rfkfiybrjd47";


    public static String getResultReportForm() {
        return MAIN_PATH + "/" + RESULT_REPORT_FILENAME;
    }

    public static String getAddressFrom() {
        return addressFrom;
    }

    public static String getPasswordFrom() {
        return passwordFrom;
    }

}




