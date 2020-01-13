package com.springapp.mvc.controller;


import com.springapp.mvc.model.*;
import com.springapp.mvc.model.Point;
import com.springapp.mvc.vspom.DateVspom;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.Entity;
import java.awt.*;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.YearMonth;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AdminController extends MainController {

    
    @RequestMapping(value = "/countReportsInput")
    public String countReportsInput(Model model){
        model.addAttribute("months", DateVspom.getMonths());
        model.addAttribute("attr", new CountAttr());
        model.addAttribute("drivers", this.userService.getAllDrivers());
        model.addAttribute("currentYear", YearMonth.now().getYear());
        return "countReportsInput";

    }

    @RequestMapping(value = "/countReportsSave")
    public String countReportsSave(Model model, CountAttr attr) {
        model.addAttribute("text", countReports(attr.getMonthNumber(), attr.getYear(), attr.getUserId()));
        return "close";
    }


    public String countReports(int monthNumber, int yearNumber, long userId){
        String result = "report(s) not found";
        if(userId > 0){
            User user = this.userService.findByIdUser(userId);
            Report report = this.reportService.getReportByUserByYearByMonth(user, yearNumber, monthNumber);
            if(report != null){
                    for(Point point: this.reportService.allPointsByReport(report)){
                         point.setDistance(this.pointService.knowTheDistance(point));
                            this.pointService.updatePoint(point);
                }
                result = "success";
            }
        }

        else {
            List<Report> reports = this.reportService.getAllReportsByYearByMonth(yearNumber, monthNumber);
            for(Report report: reports){
                    for(Point point: this.reportService.allPointsByReport(report)){
                            point.setDistance(this.pointService.knowTheDistance(point));
                            this.pointService.updatePoint(point);
                }
                result = "success";
            }
        }

        return result;

    }


}
