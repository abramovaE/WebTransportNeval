package com.springapp.mvc.controller;

import com.springapp.mvc.model.CostByZone;
import com.springapp.mvc.service.CostByZoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by oem on 12.12.17.
 */

@Controller
public class CostByZoneController extends MainController{


    @RequestMapping(value = "/costByZone", method = RequestMethod.GET)
    public String costByZone(Model model){
        model.addAttribute("costByZoneList", this.costByZoneService.getAllCosts());
        model.addAttribute("costByZone", new CostByZone());
        return "costByZone";
    }


    @RequestMapping(value = "/addZone", method = RequestMethod.GET)
    public String addZone(Model model) {
        model.addAttribute("costByZone", new CostByZone());
        model.addAttribute("title", "Добавить зону");
        return "addOrEditZone";
    }

    @RequestMapping(value = "/editCostByZone/{costByZoneId}", method = RequestMethod.GET)
    public String editCostByZone(Model model, @PathVariable("costByZoneId") long costByZoneId){
        CostByZone costByZone = this.costByZoneService.findZoneById(costByZoneId);
        model.addAttribute("costByZone",costByZone);
        model.addAttribute("title", "Изменить зону");
        return "addOrEditZone";
    }


    @RequestMapping(value = "/saveZone", method = RequestMethod.POST)
    public String saveZone(Model model, @ModelAttribute("costByZone") CostByZone costByZone){
        if(this.costByZoneService.findZoneById(costByZone.getId()) == null){
            costByZoneService.addNewZone(costByZone);
        }
        else {
            this.costByZoneService.updateCostByZone(costByZone);
        }
        return "redirect:/costByZone";
    }






}
