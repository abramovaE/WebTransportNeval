package com.springapp.mvc.controller;

import com.springapp.mvc.model.Report;
import com.springapp.mvc.model.User;
import com.springapp.mvc.vspom.DateVspom;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.time.LocalDate;
import java.util.*;

/**
 * Created by oem on 16.08.18.
 */

@Controller
public class MobileController extends MainController {


    @RequestMapping(value = "/mobile/{year}", method = RequestMethod.GET)
    public String mobile(Model model, @PathVariable("year") int year){
        model.addAttribute("year", year);
        model.addAttribute("months", DateVspom.getAllMonths());
        model.addAttribute("reportsMap", this.userService.getMapForMobile(year));
        model.addAttribute("report", new Report());
        return "mobile";
    }


    @RequestMapping(value = "/mobile/add/{reportId}", method = RequestMethod.GET)
    public String mobileAdd(Model model, @PathVariable("reportId") long reportId){
        Report report = this.reportService.findByIdReport(reportId);
        model.addAttribute("report", report);
        return "addMobile";
    }

    @RequestMapping(value = "/mobile/save/{reportId}", method = RequestMethod.POST)
    public String mobileSave(Model model, @PathVariable("reportId") long reportId, @ModelAttribute("report") Report report){
        Report oldReport = this.reportService.findByIdReport(reportId);
        oldReport.setMobile(report.getMobile());
        this.reportService.updateReport(oldReport);
//        String[] period = oldReport.getPeriod().split(" ");
        int year = oldReport.getYear();
        return "redirect:/mobile/" + year;
    }









//
//    @RequestMapping(value = {"/selectMobileYear"}, method = RequestMethod.GET)
//    public String selectMobileYear(Model model){
//        List<Integer> years = new ArrayList<>();
//        int year = LocalDate.now().getYear();
//        for(int i = 2017; i< year+1; i++){
//            years.add(i);
//        }
//        model.addAttribute("title", "Выберите год");
//        model.addAttribute("years", years);
//        model.addAttribute("period", new String());
//        return "selectMobileYear";
//    }




//
//    @RequestMapping(value = "/mobile/{year}", method = RequestMethod.GET)
//    public String mobile(Model model, @PathVariable("year") int year){
//        List<Integer> numberOfWeeks = new ArrayList<>();
//        for(int i=0; i<11; i++) {
//            numberOfWeeks.add(i);
//        }
//        model.addAttribute("year", year);
//        model.addAttribute("months", DateVspom.getAllMonths());
//        model.addAttribute("reportsMap", this.userService.getMapForMobile(year));
//        model.addAttribute("numberOfWeeks", numberOfWeeks);
//        return "mobile";
//    }
}
