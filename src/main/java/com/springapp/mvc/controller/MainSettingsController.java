package com.springapp.mvc.controller;

import com.springapp.mvc.model.AdressesCoordinates;
import com.springapp.mvc.model.MainSettings;
import com.springapp.mvc.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by oem on 10.10.18.
 */
@Controller
public class MainSettingsController extends  MainController {


    @RequestMapping(value = {"/mainSettings"}, method = RequestMethod.GET)
    public String mainSettings(Model model){
        MainSettings ms = mainSettingsService.getCurrentMS();
        if (ms == null){
            ms = new MainSettings();
        }
        List<AdressesCoordinates> allAdresses = this.adressesCoordinatesService.getAllAdresssesWithOfficePriority();
        List<User> allUsers = this.userService.getAllUsers();

        model.addAttribute("ms", ms);
        model.addAttribute("allAdresses", allAdresses);
        model.addAttribute("allUsers", allUsers);



        return "mainSettings";
    }


    @RequestMapping(value = {"/mainSettings/save"}, method = RequestMethod.POST)
    public String mainSettingsSave(Model model, @ModelAttribute("ms") MainSettings mainSettings){



        AdressesCoordinates techOffice = mainSettings.getTechOffice();
        AdressesCoordinates officialOffice = mainSettings.getOfficialOffice();


        User genDir = this.userService.findByUsername(mainSettings.getGenDirUsername());
        User finDir = this.userService.findByUsername(mainSettings.getFinDirUsername());
        User glavBuh = this.userService.findByUsername(mainSettings.getGlavBuhUsername());



        mainSettings.setTechOffice(techOffice);
        mainSettings.setOfficialOffice(officialOffice);
        mainSettings.setGenDir(genDir);
        mainSettings.setFinDir(finDir);
        mainSettings.setGlavBuh(glavBuh);

        if (this.mainSettingsService.findMSById(mainSettings.getId()) == null){
            this.mainSettingsService.saveMS(mainSettings);
        }

        else {

            this.mainSettingsService.updateMS(mainSettings);
        }

        return "redirect:/mainSettings";
    }


}


