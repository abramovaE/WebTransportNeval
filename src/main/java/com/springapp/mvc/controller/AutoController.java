package com.springapp.mvc.controller;

import com.springapp.mvc.enums.*;
import com.springapp.mvc.model.Auto;
import com.springapp.mvc.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by oem on 29.10.18.
 */
@Controller
public class AutoController extends MainController {

    @RequestMapping(value = "/addAuto/{userId}", method = RequestMethod.GET)
    public String addAuto(Model model, @PathVariable("userId") Long userId){
        User user = this.userService.findByIdUser(userId);
        Auto auto = new Auto();
        User autorizedUser = (User) model.asMap().get("autorizedUser");
        Boolean isAdmin = (Boolean) model.asMap().get("isAdmin");
        if(user.equals(autorizedUser) || isAdmin) {
            auto.setUser(user);
            model.addAttribute("auto", auto);
            model.addAttribute("user", user);
            model.addAttribute("currentAuto", true);
            setAutoAttributes(model);
            model.addAttribute("title", "Добавить новый автомобиль");
            return "addOrEditAuto";
        }
        else {
            model.addAttribute("text", "Доступ к данным другого пользователя запрещен");
            return "redirect:/welcome";
        }
    }



    @RequestMapping(value = "/editAuto/{autoId}", method = RequestMethod.GET)
    public String myAutos(Model model, @PathVariable("autoId") long autoId){
        Auto auto = this.autoService.findAutoById(autoId);
        User user = auto.getUser();
        if(user.equals(autorizedUser) || isAdmin) {
            model.addAttribute("auto", auto);
            model.addAttribute("user", user);
            setAutoAttributes(model);
            model.addAttribute("title", "Изменить данные автомобиля");
            return "addOrEditAuto";
        }

        else {
            model.addAttribute("text", "Доступ к данным другого пользователя запрещен");
            return "redirect:/welcome";
        }
    }



    @RequestMapping(value = "/saveAuto", method = RequestMethod.POST)
    public String saveAuto(Model model, @ModelAttribute("auto") Auto auto){
        User user = this.userService.findByIdUser(auto.getUser().getId());
        Auto oldAuto = this.autoService.findAutoById(auto.getId());
        boolean hasClosed = false;

        if(isAdmin){
            this.autoService.updateAuto(auto, autorizedUser);
        }

        else {
            if(oldAuto != null){
                hasClosed = this.autoService.hasClosedReports(auto);
            }

            if(oldAuto == null || (hasClosed && !oldAuto.equals(auto))){
                this.autoService.saveAuto(auto);
                if(hasClosed || auto.getCurrentAuto()){
                    user.setCurrentAuto(auto);
                    this.userService.saveChangesLog(user, autorizedUser);
                    this.userService.updateUser(user);
                }
            }


            else {
                if(!hasClosed){
                    this.autoService.updateAuto(auto, autorizedUser);

                }
            }

        }



        return "redirect:/usersManaging/editUser/" + auto.getUser().getId();


    }



    private void setAutoAttributes(Model model){
        model.addAttribute("accTypes", AccountancyTypes.values());
        model.addAttribute("bodyTypes", BodyTypes.values());
        model.addAttribute("climateMachineTypes", ClimateMachineTypes.values());
        model.addAttribute("fuelTypes", FuelTypes.values());
        model.addAttribute("transmissionTypes", TransmissionTypes.values());
    }


}
