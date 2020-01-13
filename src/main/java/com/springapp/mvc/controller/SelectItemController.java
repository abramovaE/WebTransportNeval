package com.springapp.mvc.controller;

import com.springapp.mvc.model.Auto;
import com.springapp.mvc.model.Report;
import com.springapp.mvc.model.User;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.*;

/**
 * Created by oem on 16.08.18.
 */
@Controller
public class SelectItemController extends MainController{


    @RequestMapping(value = {"/selectItem/{name}"}, method = RequestMethod.GET)
    public String selectItem(Model model, @PathVariable("name") String name){

        List<Integer> years = getYearsList();
        String title = "";
        List list = new ArrayList<>();
        String urlValue = "";

        switch (name){
            case "mobileYear":
                title = "Выберите год";
                list = years;
                urlValue = "/mobile/";
                model.addAttribute("period", new String());
                break;

            case "fuelYear":
                title = "Выберите год";
                list = years;
                urlValue = "/menuOfFuel/";
                model.addAttribute("period", new String());
                break;

            case "menuOfReports":
                List<Report> allReports = this.reportService.getAllReports();
                Set<String> periods = new LinkedHashSet<>();
                for(Report report: allReports){
                    if(isBuhgalter) {
                        if (report.getUser().getId() == 35 || (report.getUser().getId() == 36)) {
                            periods.add(report.getPeriod());
                        }
                    }
                    else {
                        periods.add(report.getPeriod());
                    }
                }

                title = "Выберите период";
                list = new LinkedList<>();
                list.addAll(periods);
                urlValue = "/managerReportsManaging/";
                model.addAttribute("period", new String());
                break;

            case "selectDriver":
                List<User> drivers = this.userService.getAllDrivers();
                title = "Выберите водителя";
                list = drivers;
                urlValue = "/calculationFuelNorm/";
                model.addAttribute("user", new User());
                break;


            case "menuOfUsersAuto":
                List<Auto> autos = this.autoService.getAllAutosByUser((User) model.asMap().get("autorizedUser"));

           title = "Выберите автомобиль";
                list = autos;
                urlValue = "/editAuto/";
                model.addAttribute("auto", new Auto());
                break;
        }


        model.addAttribute("title", title);
        model.addAttribute("list", list);
        model.addAttribute("urlValue", urlValue);
        return "selectItem";
    }


    @RequestMapping(value = {"/selectItem/{name}/{id}"}, method = RequestMethod.GET)
    public String selectItemTwo(Model model, @PathVariable("name") String name, @PathVariable("id") Long id){

        String title = "";
        List list = new ArrayList<>();
        String urlValue = "";

        switch (name){

            case "menuOfUsersAuto":
                User user = this.userService.findByIdUser(id);
                List<Auto> userAutos = addUserAutosToModel(model, user);
                title = "Выберите автомобиль";
                list = userAutos;
                urlValue = "/editAuto/";
                model.addAttribute("auto", new Auto());
                getTopMenuButtons().put("Добавить автомобиль", "/addAuto/" + id);
                break;
        }
        model.addAttribute("title", title);
        model.addAttribute("list", list);
        model.addAttribute("urlValue", urlValue);
        return "selectItem";
    }


    private List<Integer> getYearsList(){
        int year = LocalDate.now().getYear();
        List<Integer> years = new ArrayList<>();
        for(int i = 2017; i< year+1; i++){
            years.add(i);
        }
        return years;
    }


}
