package com.springapp.mvc.controller;

import com.springapp.mvc.model.Day;
import com.springapp.mvc.model.Point;
import com.springapp.mvc.model.Report;
import com.springapp.mvc.enums.BodyTypes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.time.DateTimeException;
import java.time.LocalDate;

/**
 * Created by oem on 12.12.17.
 */

@Controller
public class DayController extends MainController{



    @RequestMapping(value = "/addNewDay/{reportId}/save", method = RequestMethod.POST)
    public String saveOrUpdateDayAction(@PathVariable("reportId") long reportId, @ModelAttribute("day") Day day, Model model) {

        Boolean isAdmin = (Boolean) model.asMap().get("isAdmin");
        Boolean isFinDir = (Boolean) model.asMap().get("isFinDir");
        Report report = this.reportService.findByIdReport(reportId);


//            if(day.getDate().length() < 3) {
//                try {
//                    LocalDate date = LocalDate.of(report.getYear(), report.getMonthNumber(), Integer.parseInt(day.getDate()));
//                    day.setDate(date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
//                } catch (DateTimeException e) {
//                    model.addAttribute("text", "Вы ввели ошибочную дату");
//                    return "redirect:/showReport/" + report.getId();
//                }
//            }
//


        if(day.getDayNumber() != null && day.getDayNumber() != 0){
            try {
            LocalDate.of(report.getYear(), report.getMonthNumber(), day.getDayNumber());
            } catch (DateTimeException e) {
                model.addAttribute("text", "Вы ввели ошибочную дату");
                return "redirect:/showReport/" + report.getId();
            }
        }



        day.setReport(report);
        if(this.dayService.findByIdDay(day.getId()) == null){
            this.dayService.saveDay(day);
            model.addAttribute("newpoint", new Point());
            return "redirect:/addtheroute/" + day.getId();
        }

        else {
            if(isAdmin || isFinDir){
                this.dayService.updateDay(day, true);
                if(report.isClosed()) {
                    return "redirect:/editClosedReport/" + reportId;
                }
                else {
                    return "redirect:/showReport/" + reportId;
                }
            }
            else {
                this.dayService.updateDay(day);
            }
            return "redirect:/showReport/" + reportId;
        }
    }



    @RequestMapping(value = {"/editDay/{dayId}"}, method = RequestMethod.GET)
    public String editDay(Model model, @PathVariable("dayId") long dayId){


        if(autorizedUser.getBlocked()){
            return "redirect:/welcome";
        }


        Day day = this.dayService.findByIdDay(dayId);
        Report report = day.getReport();

//        System.out.println(autorizedUser);
//        System.out.println(report.getUser());


        this.dayService.updateDay(day);
        model.addAttribute("day", day);
        model.addAttribute("report", report);
        model.addAttribute("point", new Point());
        model.addAttribute("points", this.dayService.allPointsByDay(day));
        model.addAttribute("isMinibus", day.getReport().getAuto().getBodyType().equals(BodyTypes.MINIBUS.getName()));


        if(day.getReport().isClosed()) {
            if(isAdmin || isManager){
                return "editDay";
            }
            else {
                String text = "Этот отчет запрещен к редактированию";
                model.addAttribute("text", text);
                return "redirect:/reportsManaging";
            }
        }


        else if(!autorizedUser.equals(report.getUser()) && !isAdmin && !isManager && !isBuhgalter){
            String text = "Вы не можете редактировать отчет другого пользователя";
            model.addAttribute("text", text);
            return "redirect:/reportsManaging";
        }
        return "editDay";
    }

    @RequestMapping(value = {"/deleteDay/{dayId}"})
    public String deleteNewDay(Model model, @PathVariable("dayId") long dayId){
        long reportId = this.dayService.findByIdDay(dayId).getReport().getId();
        this.dayService.deleteDay(dayId);
        return "redirect:/showReport/" + reportId;
    }
}
