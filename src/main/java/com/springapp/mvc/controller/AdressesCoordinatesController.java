package com.springapp.mvc.controller;

import com.springapp.mvc.model.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by kot on 10.07.17.
 */

@Controller
public class AdressesCoordinatesController extends MainController{

    @RequestMapping(value = "/addressesManaging", method = RequestMethod.GET)
    public String adressesManaging(Model model){
        model.addAttribute("adressesList",this.adressesCoordinatesService.getAllAdresses());
        model.addAttribute("adressCoordinates", new AdressesCoordinates());
        return "addressesManaging";
    }
    
    @RequestMapping(value = "/addAdress", method = RequestMethod.GET)
    public String addAdress(Model model){
        model.addAttribute("address", new AdressesCoordinates());
        model.addAttribute("title", "Добавить новый адрес");
        return "addOrEditAdress";
    }


    @RequestMapping(value = "/editAdress/{addressId}", method = RequestMethod.GET)
    public String editAdress(Model model, @PathVariable("addressId") long adressId){
        AdressesCoordinates adressesCoordinates = this.adressesCoordinatesService.findByIdAdressCoordinates(adressId);
        model.addAttribute("address", adressesCoordinates);
        model.addAttribute("title", "Редактировать адрес");
        return "addOrEditAdress";
    }


    @RequestMapping(value = "/saveAdress", method = RequestMethod.POST)
    public String saveAdress(Model model, @ModelAttribute("address") AdressesCoordinates adressesCoordinates) {

        AdressesCoordinates adressesCoordinatesOld = this.adressesCoordinatesService.findByIdAdressCoordinates(adressesCoordinates.getId());
        if (adressesCoordinatesOld == null) {
            adressesCoordinatesService.addNewAdressCoordinates(adressesCoordinates);
        } else {

            String oldAdress = adressesCoordinatesOld.getAddress().trim();
            String newAdress = adressesCoordinates.getAddress().trim();
            StringBuilder mes = new StringBuilder();
            if (!oldAdress.equals(newAdress)) {
                mes.append("Адрес \"" + oldAdress + "\" изменен на \"" + newAdress + "\"");
            }
            this.adressesCoordinatesService.updateAdressCoordinates(adressesCoordinates);
            model.addAttribute("text", mes.toString());
        }
        return "redirect:/addressesManaging";
    }

    @RequestMapping(value = "/useForAutoReports/{adressId}")
    public String useForAutoReports(Model model, @PathVariable("adressId") long adressId){
        AdressesCoordinates adressesCoordinates = this.adressesCoordinatesService.findByIdAdressCoordinates(adressId);
        adressesCoordinates.setUsedForAutoReports(true);
        this.adressesCoordinatesService.updateAdressCoordinates(adressesCoordinates);
        return "redirect:/addressesManaging";
    }
}
