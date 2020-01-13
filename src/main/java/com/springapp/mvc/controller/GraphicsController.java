package com.springapp.mvc.controller;

import com.springapp.mvc.model.Report;
import com.springapp.mvc.model.User;
import com.springapp.mvc.model.UserGraphics;
import com.springapp.mvc.vspom.DateVspom;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.*;

/**
 * Created by oem on 16.08.18.
 */
@Controller
public class GraphicsController extends MainController {





    @RequestMapping(value = {"/graphicsOfUsersFuel/{year}/{userId}"}, method = RequestMethod.GET)
    public String graphicsOfUsersFuel(Model model, @PathVariable("userId") long userId, @PathVariable("year") int year){

        User user = this.userService.findByIdUser(userId);
        Set<String> months = DateVspom.getAllMonths();
        List<Report> listOfUserReports = new LinkedList<>();
        List<Double> listOfTotalSumm = new LinkedList<>();
        List<Double> listOfCompany = new LinkedList<>();

        for(String month : months){
            Report report = this.userService.getReportByUserByPeriod(user, month + " " + year);



            if(report != null) {
                this.reportService.fillTheReport(report);
                report = this.reportService.getReportForShow(report);
                listOfCompany.add(report.getCompany().doubleValue());
            }
            else
            {
                listOfCompany.add(0d);
            }

            if(report != null){
                listOfTotalSumm.add(report.getSumSumm());
            }
            else {
                listOfTotalSumm.add(0d);
            }
            listOfUserReports.add(report);
        }
        model.addAttribute("user", user);
        model.addAttribute("userReports", listOfUserReports);
        model.addAttribute("year", year);
        model.addAttribute("months", months);
        model.addAttribute("listOfTotalSumm", listOfTotalSumm);
        model.addAttribute("listOfCompany", listOfCompany);
        return "graphicsOfUsersFuel";
    }


    @RequestMapping(value = {"/graphicsOfUsersFuel/{year}"}, method = RequestMethod.GET)
    public String graphicsOfUsersFuel(Model model, @PathVariable("year") int year){

        List<User> users = this.userService.getAllDrivers();
        Map<User, UserGraphics> map = new LinkedHashMap<>();

        Set<String> months = DateVspom.getAllMonths();

        for(User user:users){
            UserGraphics userGraphics = new UserGraphics();
            List<Report> listOfUserReports = new LinkedList<>();
            List<Double> listOfTotalSumm = new LinkedList<>();
            List<Double> listOfCompany = new LinkedList<>();

            for(String month : months){
                Report report = this.userService.getReportByUserByPeriod(user, month + " " + year);
                if(report != null) {
                    this.reportService.fillTheReport(report);
                    report = this.reportService.getReportForShow(report);
                    listOfCompany.add(report.getCompany().doubleValue());
                }
                else
                {
                    listOfCompany.add(0d);
                }
                if(report != null && report.getSumSumm() != null) {
                    listOfTotalSumm.add(report.getSumSumm());
                }

                else {
                    listOfTotalSumm.add(0d);
                }

                listOfUserReports.add(report);
            }
            userGraphics.setListOfUserReports(listOfUserReports);
            userGraphics.setListOfCompany(listOfCompany);
            userGraphics.setListOfTotalSumm(listOfTotalSumm);
            map.put(user, userGraphics);
        }

        model.addAttribute("year", year);
        model.addAttribute("months", DateVspom.getAllMonths());
        model.addAttribute("graphicsMap", map);
        return "graphicsOfUsersFuelAll";
    }

}
