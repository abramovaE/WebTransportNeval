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

/**
 * Created by oem on 23.02.19.
 */
@Controller
public class ExpensesController extends MainController{


    @RequestMapping(value = "/showExpenses", method = RequestMethod.GET)
    public String showExpenses(Model model){
        model.addAttribute("addressesList", this.adressesCoordinatesService.getAllAdresses());
        model.addAttribute("userList", this.userService.getAllDrivers());
        model.addAttribute("monthsList", DateVspom.getAllMonthWithMonthPriority(0));
        model.addAttribute("address", new AdressesCoordinates());
        model.addAttribute("user", new User());
        model.addAttribute("title", "Показать затраты на адрес");
        model.addAttribute("exp", new Expense());
        return "expenses";
    }






    @RequestMapping(value = "/showExpenses/save", method = RequestMethod.POST)
    public String showExpensesSave(Model model, @ModelAttribute("exp") Expense expense){

        long start = System.currentTimeMillis();

        int startDay = expense.getStartDay();
        int endDay = expense.getEndDay();

        int startMonth = DateVspom.getNumberOfMonth(expense.getStartMonth());
        int endMonth = DateVspom.getNumberOfMonth(expense.getEndMonth());

        int startYear = expense.getStartYear();
        int endYear = expense.getEndYear();

        List<Report> reports = this.reportService.getAllReportsByUser(expense.getUser());
        List<Point> resPoints = new ArrayList<>();
        BigDecimal resPointsSum = new BigDecimal("0");

        LocalDate startDate = LocalDate.of(startYear, startMonth, startDay);
        LocalDate endDate = LocalDate.of(endYear, endMonth, endDay);

        YearMonth startYearMonth = YearMonth.of(startYear, startMonth);
        YearMonth endYearMonth = YearMonth.of(endYear, endMonth);

        for(Report report: reports){
            YearMonth yearMonth = report.getYearMonth();
            if(yearMonth.compareTo(startYearMonth) == 0
                    || (yearMonth.isAfter(startYearMonth) && yearMonth.isBefore(endYearMonth)
                    || yearMonth.compareTo(endYearMonth) == 0)){

                List<Point> points = this.reportService.allPointsByReport(report, expense.getAddress());
                for(Point p :points){

//                    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
//                    LocalDate dayDate = LocalDate.parse(p.getDay().getDate(), dateTimeFormatter);
//
                    LocalDate dayDate = LocalDate.of(report.getYear(), report.getMonthNumber(), p.getDay().getDayNumber());

                    if((dayDate.isEqual(startDate) || dayDate.isAfter(startDate)) && (dayDate.isBefore(endDate) || dayDate.isEqual(endDate))){
                        resPoints.add(p);
                        resPointsSum = resPointsSum.add(p.getExpensePrice());
                    }
                }
            }
        }




        Collections.sort(resPoints, new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
//                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");



//                LocalDate date1 = LocalDate.parse(o1.getDay().getDate(), dateTimeFormatter);
//                LocalDate date2 = LocalDate.parse(o2.getDay().getDate(), dateTimeFormatter);

                int date1 = o1.getDay().getDayNumber();
                int date2 = o2.getDay().getDayNumber();
                return date1 - date2;
            }
        });


        model.addAttribute("points", resPoints);

        model.addAttribute("pointsSum", resPointsSum.doubleValue());
        model.addAttribute("addressesList", this.adressesCoordinatesService.getAllAdresses());
        model.addAttribute("userList", this.userService.getAllDrivers());
        model.addAttribute("monthsList", DateVspom.getAllMonthWithMonthPriority(0));
        model.addAttribute("address", new AdressesCoordinates());
        model.addAttribute("user", new User());
        model.addAttribute("title", "Показать затраты на адрес");
        model.addAttribute("exp", new Expense());

        model.addAttribute("titleAdr", this.adressesCoordinatesService.findByIdAdressCoordinates(expense.getAddress().getId()).getAddress());


        long res  = System.currentTimeMillis() - start;
        System.out.println("res " + res);

        return "expenses";
    }

    private String getDateSubsrt(int day){
        if(day < 10){
            return "0" + day;
        }
        return "" + day;
    }

}
