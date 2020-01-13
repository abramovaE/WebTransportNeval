package com.springapp.mvc.controller;

import com.springapp.mvc.emailWorker.SendEMail;
import com.springapp.mvc.fns.*;
import com.springapp.mvc.model.*;

import com.springapp.mvc.vspom.Constants;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.util.SocketUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.*;

/**
 * Created by oem on 12.04.18.
 */
@Controller
public class WelcomeController extends MainController {

    @RequestMapping(value = {"/","/welcome"}, method = RequestMethod.GET)
    public String welcome(Model model){

        if(isAdmin || isManager){
            model.addAttribute("message", new Message());
        }

        if(autorizedUser.getBlocked()){
            String text = "Внимание! Ваша учетная запись заблокирована, внесение изменений и создание новых отчетов невозможно. " +
                    "Пожалуйста, обратитесь к Абрамову С.В.";
            model.addAttribute("text", text);
        }
        model.addAttribute("username", userService.getfullUserName(autorizedUser));
        return "welcome";
    }



    @RequestMapping(value = {"/reportsManaging"}, method = RequestMethod.GET)
    public String reportsManaging(Model model){
        if(autorizedUser.getBlocked()){
            return "redirect:/welcome";
        }
        if(!autorizedUser.getBlocked()){
            String fullName = userService.getfullUserName(autorizedUser);
            List<Report> reports = this.userService.allReportsByUserRevSort(autorizedUser);
            model.addAttribute("name", fullName);
            model.addAttribute("reportsList", reports);
            model.addAttribute("report", new Report());
            return "reportsManaging";
        }
        else {
            return "redirect:/welcome";
        }
    }


    @RequestMapping(value = {"/reportsManaging/{userId}"}, method = RequestMethod.GET)
    public String reportsManaging(Model model, @PathVariable("userId") long userId){
        User user = userService.findByIdUser(userId);
        if(isBuhgalter){
            List<Report> reports = this.userService.allReportsByUserRevSort(user);
            model.addAttribute("reportsList", reports);
            model.addAttribute("report", new Report());
            return "reportsManaging";
        }
        else {
            return "redirect:/welcome";
        }
    }

    @RequestMapping(value = {"/menuOfFuel/{year}"}, method = RequestMethod.GET)
    public String menuOfFuel(Model model, @PathVariable("year") int year){
        List<User> users = this.userService.getAllDrivers();
        model.addAttribute("users", users);
        model.addAttribute("user", new User());
        model.addAttribute("year", year);
        return "menuOfFuel";
    }


    @RequestMapping(value = "/writeToDeveloper", method = RequestMethod.POST)
    public String writeToDeveloperSend(Model model, @ModelAttribute("message") Message message){
        StringBuilder recepients = new StringBuilder(this.mainSettingsService.getCurrentMS().getDevEmailAdress());
        String text = "Ваше письмо отправлено разработчику";
        if(message.isCopyToManager()){
            recepients.append(", " + autorizedUser.geteMail());
        }
        try {
            SendEMail.sendMailWithoutAttachment(Constants.getAddressFrom(), Constants.getPasswordFrom(), recepients.toString(), message,
                    autorizedUser.getShortFullName());
        } catch (MessagingException e) {
            text = "Ваше письмо не было отправлено";
        }
        model.addAttribute("text", text);
        return "redirect:/welcome";
    }

}
