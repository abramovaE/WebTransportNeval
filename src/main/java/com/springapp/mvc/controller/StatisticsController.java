package com.springapp.mvc.controller;


import com.springapp.mvc.model.*;
import com.springapp.mvc.vspom.DateVspom;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Controller
public class StatisticsController extends MainController{






    @RequestMapping(value = "/showStatistic", method = RequestMethod.GET)
    public String showStatistic(Model model){
        model.addAttribute("drivers", this.userService.getAllDrivers());
        model.addAttribute("monthsList", DateVspom.getAllMonthWithMonthPriority(0));
        model.addAttribute("title", "Показать статистику");
        model.addAttribute("stat", new Statistic());
        return "chooseStatistic";
    }



    @RequestMapping(value = "/showStatistic", method = RequestMethod.POST)
    public String showStatistic(Model model, Statistic statistic){

        int startMonth = DateVspom.getNumberOfMonth(statistic.getStartMonth());
        int endMonth = DateVspom.getNumberOfMonth(statistic.getEndMonth());
        int startYear = statistic.getStartYear();
        int endYear = statistic.getEndYear();

        YearMonth startYearMonth = YearMonth.of(startYear, startMonth);
        YearMonth endYearMonth = YearMonth.of(endYear, endMonth);
        List<Report> reports = this.reportService.getAllReports();
        List<StatisticUser> users = new ArrayList<>();
        for(long id: statistic.getUsersId()){

            User user = this.userService.findByIdUser(id);
            StatisticUser statisticUser = new StatisticUser();

            BigDecimal company = new BigDecimal(0);
            BigDecimal totalAmort = new BigDecimal(0);
            int workingDays = 0;

            for(Report report: reports){
                if(report.getUser().getId() == user.getId()){
                    YearMonth yearMonth = report.getYearMonth();
                    if(yearMonth.compareTo(startYearMonth) == 0
                            || (yearMonth.isAfter(startYearMonth) && yearMonth.isBefore(endYearMonth)
                            || yearMonth.compareTo(endYearMonth) == 0)){

                        company = company.add(report.getCompany());
                        totalAmort = totalAmort.add(this.reportService.getTotalAmort(report));
                        workingDays += this.reportService.allDaysByReport(report).size();
                    }
                }
            }



            statisticUser.setUser(user);
            statisticUser.setCompany(company.doubleValue());
            statisticUser.setTotalAmort(totalAmort.doubleValue());
            statisticUser.setWorkingDays(workingDays);
            users.add(statisticUser);
        }

        model.addAttribute("users", users);
        model.addAttribute("title", "Статистика за период: " + startYearMonth + " - " + endYearMonth);


        return "/showStatistic";
    }




}
