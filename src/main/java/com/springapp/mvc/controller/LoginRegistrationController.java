package com.springapp.mvc.controller;

import com.springapp.mvc.model.*;
import com.springapp.mvc.service.*;
import com.springapp.mvc.validator.UserValidator;
import com.springapp.mvc.vspom.Vspom;
import org.springframework.beans.PropertyEditorRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.validation.MessageCodesResolver;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Locale;


/**
 * Created by kot on 22.03.17.
 */
@Controller
public class LoginRegistrationController extends MainController {


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if(error != null){
            model.addAttribute("error", "Username or password is incorrect");
        }
        if(logout != null){
            model.addAttribute("message", "Logged out successfully");
        }
        return "login";
    }


    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model, @ModelAttribute("text") String text) {
        model.addAttribute("newUser", new User());
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(Model model, @ModelAttribute("newUser") User newUser, BindingResult bindingResult) {

        userValidator.validate(newUser, bindingResult);

        if(bindingResult.hasErrors()){
            model.addAttribute("text", messageSource.getMessage(bindingResult.getAllErrors().get(0), Locale.getDefault()));
//            return "redirect:/registration";
            return "registration";
//
        }
        else {
            userService.save(newUser);
            return "redirect:/usersManaging/other";
        }
    }


    @RequestMapping(value = {"/info"}, method = RequestMethod.GET)
    public String info(){
        return "info";
    }


}