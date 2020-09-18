package com.springapp.mvc.controller;

import com.springapp.mvc.enums.*;
import com.springapp.mvc.model.*;
import com.springapp.mvc.vspom.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.YearMonth;
import java.util.*;

/**
 * Created by oem on 14.02.18.
 */
@Controller
@RequestMapping(value = "/usersManaging")
public class UsersManagingController extends MainController
{

    @RequestMapping(value = "/{usersType}")
    public String usersManaging(Model model, @PathVariable("usersType") String usersType)
    {
        List<User> users;
        switch (usersType){
            case "drivers":
                users = this.userService.getAllDrivers();
                break;

            case "other":
                users = this.userService.getAllNotDrivers();
                break;

            default:
                users = this.userService.getAllUsers("surname");
                break;
        }
        model.addAttribute("user" , new User());
        model.addAttribute("usersList", users);
        model.addAttribute("usersType", usersType);
        return "usersManaging";
    }


    @RequestMapping(value = "/delUser/{userId}")
    public String delUser(Model model, @PathVariable("userId") long userId) {
        User user = this.userService.findByIdUser(userId);

        Map<RoleEnum, Boolean> userRolesMap = this.userService.getRolesMap(user);
        boolean isUserAdmin = userRolesMap.get(RoleEnum.ROLE_ADMIN);
        boolean isUserFinDir = userRolesMap.get(RoleEnum.ROLE_FINDIR);
        boolean isUserBuhgalter = userRolesMap.get(RoleEnum.ROLE_BUHGALTER);
        boolean isUserManager = userRolesMap.get(RoleEnum.ROLE_MANAGER);

        if(isAdmin) {
            List<Report> allReportsByUser = this.reportService.getAllReportsByUser(user);

            if(allReportsByUser.isEmpty() && !isUserAdmin && !isUserFinDir && !isUserBuhgalter && !isUserManager) {
                this.userRolesService.deleteUserRoles(userId);
                this.userService.delUser(userId);
            }

            else if(!allReportsByUser.isEmpty()) {
                model.addAttribute("text", "Нельзя удалить пользователя, у которого есть отчеты");
            }

            else {
                model.addAttribute("text", "Нельзя удалить менеджера, бухгалтера, фин.директора или админа");
            }

            return "redirect:/usersManaging/all";
        }

        else {
            model.addAttribute("text", "Доступ к данным другого пользователя запрещен");
            return "redirect:/welcome";
        }
    }



    @RequestMapping(value = "/editUser/{userId}",method = RequestMethod.GET)
    public String editUser(Model model, @PathVariable("userId") long userId) {
        User user = this.userService.findByIdUser(userId);
        if(user.equals(autorizedUser) || isAdmin || isFinDir || isManager || isBuhgalter) {
            model.addAttribute("user", user);
            if(user.getCurrentAuto() != null || !this.userService.allAutosByUser(user).isEmpty()) {
                model.addAttribute("auto", user.getCurrentAuto());
                model.addAttribute("accTypes", AccountancyTypes.values());
                model.addAttribute("bodyTypes", BodyTypes.values());
                model.addAttribute("climateMachineTypes", ClimateMachineTypes.values());
                model.addAttribute("fuelTypes", FuelTypes.values());
                model.addAttribute("transmissionTypes", TransmissionTypes.values());

                addUserAutosToModel(model, user);
                model.addAttribute("notDriver", false);

            }
            if(isBuhgalter || isFinDir || isManager){
                model.addAttribute("notDriver", true);
            }
            return "editUser";
        }

        else {
            model.addAttribute("text", "Доступ к данным другого пользователя запрещен");
            return "redirect:/welcome";
        }
    }





    @RequestMapping(value = "/editUser/save", method = RequestMethod.POST)
    public String editUser(Model model, @ModelAttribute("user") User user){
        user.setRoles(this.userService.findByIdUser(user.getId()).getRoles());
        if(user != null){
            this.userService.saveChangesLog(user, autorizedUser);
            this.userService.updateUser(user);
        }
        else {
            model.addAttribute("text", "Пользователь не найден");
        }
        return "redirect:/usersManaging/editUser/" + user.getId();
    }



    @RequestMapping(value = "/editAuto/save", method = RequestMethod.POST)
    public String editUser(Model model
            , @ModelAttribute("auto") Auto auto
            , @RequestParam("form") String form){

        if(form.contains("uploadStsForm") || form.contains("uploadOsagoForm")){
            return "redirect:/uploadFile/" + auto.getUser().getId();
        }
        if(auto != null){
            this.autoService.updateAuto(auto, autorizedUser);
        }
        else {
            model.addAttribute("text", "Автомобиль не найден");
        }

        return "redirect:/usersManaging/editUser/" + auto.getUser().getId();
    }





    @RequestMapping(value = "/sortBy/{param}")
    public String sortByParam(Model model, @PathVariable("param")  String param, @ModelAttribute("usersType") String usersType){
        List<User> users;

        if(usersType.equals("drivers")){
            switch (param){
                case "summerNorm":
                    users = this.userService.getAllDrivers();
                    Collections.sort(users, new Comparator<User>() {
                        @Override
                        public int compare(User o1, User o2) {
                            Double d1 = o1.getCurrentAuto().getSummerNorm();
                            Double d2 = o2.getCurrentAuto().getSummerNorm();
                            if (d1 == null) d1 = 0d;
                            if (d2 == null) d2 = 0d;
                            return d1.compareTo(d2);
                        }
                    });
                    break;

                case "winterNorm":
                    users = this.userService.getAllDrivers();
                    Collections.sort(users, new Comparator<User>() {
                        @Override
                        public int compare(User o1, User o2) {
                            Double d1 = o1.getCurrentAuto().getWinterNorm();
                            Double d2 = o2.getCurrentAuto().getWinterNorm();
                            if (d1 == null) d1 = 0d;
                            if (d2 == null) d2 = 0d;
                            return d1.compareTo(d2);
                        }
                    });
                    break;

                default:
                    users = this.userService.getAllDrivers(param);
                    break;
            }


        }

        else if(usersType.equals("other")){
            users = this.userService.getAllNotDrivers("surname");
        }

        else {
            users = this.userService.getAllUsers("surname");
        }

        model.addAttribute("user" , new User());
        model.addAttribute("usersList", users);
        model.addAttribute("usersType", usersType);

        return "usersManaging";
    }



    @RequestMapping(value = "/blockUser/{userId}")
    public String blockUserGet(Model model,@PathVariable("userId") long userId) {
        User user = this.userService.findByIdUser(userId);
        this.userService.blockUser(user, autorizedUser);
        return "redirect:/usersManaging/drivers";
    }


    @RequestMapping(value = "/fireUser/{userId}")
    public String fireUserGet(Model model,@PathVariable("userId") long userId) {
        User user = this.userService.findByIdUser(userId);
        this.userService.fireUser(user, autorizedUser);
        return "redirect:/usersManaging/drivers";
    }

    @RequestMapping(value = "/setBuhgalteria/{userId}")
    public String setBuhgalteriaUser(Model model,@PathVariable("userId") long userId) {
        User user = this.userService.findByIdUser(userId);
        this.userService.setBuhgalteria(user, autorizedUser);
        return "redirect:/usersManaging/drivers";
    }

    @RequestMapping(value = "createAutoReport/{userId}/{monthNumber}/{year}")
    public String createAutoReport(Model model,@PathVariable("userId") long userId, @PathVariable("monthNumber") int monthNumber,
                                   @PathVariable("year") int year){

        User user = this.userService.findByIdUser(userId);
        List<AdressesCoordinates> allAdressesCoordinates = this.adressesCoordinatesService.getAdressesForAutoReports();
        if(allAdressesCoordinates == null || allAdressesCoordinates.isEmpty()){
            allAdressesCoordinates = this.adressesCoordinatesService.getAllAdresses();
        }

        YearMonth prevYearMonth = YearMonth.now().minusMonths(1);
        String period = DateVspom.getPeriodFromYearMonth(prevYearMonth);

        if(this.reportService.getReportByUserByYearByMonth(user, prevYearMonth.getYear(), prevYearMonth.getMonthValue()) == null) {
            Report report;
            MainSettings mainSettings = this.mainSettingsService.getCurrentMS();
            if(userId == 35 || userId == 36){
                report = Vspom.createCanvasAutoReport(allAdressesCoordinates, mainSettings, 4, true, 0, 0);
            }
            else if (userId == 13 || userId == 19){
                if(monthNumber == 0 || year == 0){
                    monthNumber = prevYearMonth.getMonthValue();
                    year = prevYearMonth.getYear();
                }
                report = Vspom.createCanvasAutoReport(allAdressesCoordinates, mainSettings, 4, false, monthNumber, year);
            }

            else {
                report = null;
            }

            report.setUser(user);
            report.setAuto(user.getCurrentAuto());
            this.reportService.saveReport(report);

            for (Day day : report.getDays()) {
                this.dayService.saveDay(day);


//            в метрах
//                int dayDistance = 0;
                for (Point point : day.getPoints()) {
                    Integer distance = pointService.knowTheDistance(point);
                    point.setDistance(distance);
                    this.pointService.saveAutoPoint(point);
                }
            }

            return "redirect:/welcome";

        }else {
            String text = "Отчет пользователя " + user.getShortFullName() + " за период " + period + " уже существует";
            model.addAttribute("text",text);
            return "redirect:/welcome";
        }
    }
}
