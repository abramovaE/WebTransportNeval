package com.springapp.mvc.controller;

import com.springapp.mvc.model.User;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.sql.SQLOutput;
import java.util.Locale;

/**
 * Created by oem on 25.06.19.
 */
@Controller
public class PasswordController extends MainController{


    @RequestMapping(value = "/changePassword", method = RequestMethod.GET)
    public String changePassword(Model model){
        return "changePassword";
    }


    @RequestMapping(value = "/changePassword/{userId}", method = RequestMethod.GET)
    public String changePassword(Model model, @PathVariable("userId") Long userId){

        User user = this.userService.findByIdUser(userId);
        model.addAttribute("user", user);
        if(isBuhgalter || isFinDir || isManager){
            model.addAttribute("notDriver", true);
        }
        return "changePassword";
    }

    @RequestMapping(value = "/changePassword/save", method = RequestMethod.POST)
    public String changePasswordSave(Model model, @ModelAttribute("user") User user, BindingResult bindingResult){
        User autorizedUser = (User) model.asMap().get("autorizedUser");


        userValidator.validateChanges(user, bindingResult);

        if(bindingResult.hasErrors()){
            model.addAttribute("text", messageSource.getMessage(bindingResult.getAllErrors().get(0), Locale.getDefault()));
            return "changePassword";
        }

        else {
            this.userService.changePassword(user, autorizedUser);
            return "redirect:/usersManaging/editUser/" + user.getId();

        }
    }
}
