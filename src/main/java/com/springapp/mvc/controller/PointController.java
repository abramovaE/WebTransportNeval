package com.springapp.mvc.controller;

import com.springapp.mvc.model.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by oem on 12.12.17.
 */

@Controller
public class PointController  extends MainController {

    @RequestMapping(value = "/addtheroute/{dayId}", method = RequestMethod.GET)
    public String admin(Model model, @PathVariable("dayId") long dayId){

        Day day = this.dayService.findByIdDay(dayId);
        List<Point> allPoints = this.dayService.allPointsByDay(day);
        Report report = day.getReport();
        Point point = new Point();

        List<AdressesCoordinates> allAdresses = this.adressesCoordinatesService.getAllAdresssesWithOfficePriority();

        Point lastPoint;
        AdressesCoordinates lastArrPoint;

        if(allPoints.isEmpty()){
            lastArrPoint = allAdresses.get(0);
        }

        else {
            lastPoint = allPoints.get(allPoints.size()-1);
            lastArrPoint = lastPoint.getArrival();
        }
        model.addAttribute("allPoints", allPoints);
        model.addAttribute("allAdresses", allAdresses);
        model.addAttribute("adress", new AdressesCoordinates());
        model.addAttribute("day", day);
        model.addAttribute("report",  report);
        model.addAttribute("point", point);
        model.addAttribute("depPoint", lastArrPoint);
        return "addtheroute";
    }




    @RequestMapping(value = "editPoint/{pointId}", method = RequestMethod.GET)
    public String editNewPoint(Model model, @PathVariable("pointId") long pointId){

        Point point = this.pointService.findByIdPoint(pointId);
        Day day = point.getDay();
        Report report = day.getReport();

        if(autorizedUser.getBlocked()){
            return "redirect:/welcome";
        }

        List<AdressesCoordinates> allAdresses = this.adressesCoordinatesService.getAllAdresssesWithOfficePriority();
        if(report.isClosed()) {
            String text = "Этот отчет запрещен к редактированию";
            model.addAttribute("text", text);
            return "redirect:/reportsManaging";
        }

        else if(autorizedUser.getId() != report.getUser().getId() && !isAdmin && !isManager && !isBuhgalter){
            String text = "Вы не можете редактировать отчет другого пользователя";
            model.addAttribute("text", text);
            return "redirect:/reportsManaging";
        }

        model.addAttribute("point", point);
        model.addAttribute("day", day);
        model.addAttribute("report", report);
        model.addAttribute("allAdresses", allAdresses);
        model.addAttribute("adress", new AdressesCoordinates());
        return "editPoint";
    }





    @RequestMapping(value = "/addtheroute/{dayId}/save", method = RequestMethod.POST)
    public String addNewPoint(Model model, @PathVariable("dayId") long dayId, @ModelAttribute("point") Point point){

        Day day = this.dayService.findByIdDay(dayId);
        AdressesCoordinates departure = this.adressesCoordinatesService.findAdressesCoordinatesByAdress(point.getDepAdress());
        AdressesCoordinates arrival = this.adressesCoordinatesService.findAdressesCoordinatesByAdress(point.getArrAdress());
        point.setDay(day);


        if (departure.getAddress() == null || arrival.getAddress() == null || departure.getAddress().isEmpty() || arrival.getAddress().isEmpty()){
            model.addAttribute("text", "Вы не можете добавить строку с пустым адресом");
            return "redirect:/addtheroute/" + dayId;
        }


        if(departure != null && arrival != null) {

            point.setDeparture(departure);
            point.setArrival(arrival);

            Integer pointDistance = this.pointService.knowTheDistance(point);
            point.setDistance(pointDistance);

            String accouncyType = day.getReport().getUser().getAccountancyType();
            if(accouncyType != null && accouncyType.equals("зональная")){
                int numberOfZoneArr = arrival.getNumberOfZone();
                int costByZoneArr = this.costByZoneService.findCostByZoneByNumberOfZone(numberOfZoneArr);
                this.pointService.calculationCostByZone(point, this.dayService.allPointsByDay(day).size(), costByZoneArr);
            }
        }

        if (pointService.findByIdPoint(point.getId()) == null) {
            this.pointService.savePoint(point);
        }


        else {
            if(point.isNeedChange()){
                point.setIsChanged(true);
            }
            this.pointService.updatePoint(point);
        }
        return "redirect:/addtheroute/" + dayId;
    }


    @RequestMapping(value = {"/editDay/deletePoint/{pointId}"})
    public String deletePointFromEdit(Model model, @PathVariable("pointId") long pointId){
        return "redirect:/editDay/" + deletePoint(pointId);
    }


    @RequestMapping(value = "/addtheroute/deletePoint/{pointId}")
    public String deletePointFromAdd(Model model, @PathVariable("pointId") long pointId){
        return "redirect:/addtheroute/" + deletePoint(pointId);
    }



//удаляет пункт маршрута, возвращает dayId для редиректа
    private long deletePoint(long pointId){
        Point point = this.pointService.findByIdPoint(pointId);
        Day day = point.getDay();
        long dayId = day.getId();
//        System.out.println(pointId);
        this.pointService.deletePoint(pointId);
        return dayId;
    }


    @RequestMapping(value = "addMediumPoint/{pointId}", method = RequestMethod.GET)
    private String addMediumPoint(Model model, @PathVariable("pointId") long pointId){


        Point currentPoint = this.pointService.findByIdPoint(pointId);


        if(autorizedUser.getBlocked()){
            return "redirect:/welcome";
        }

        if(currentPoint.getDay().getReport().isClosed()) {
            String text = "Этот отчет запрещен к редактированию";
            model.addAttribute("text", text);
            return "redirect:/reportsManaging";
        }

        else if(autorizedUser.getId() != currentPoint.getDay().getReport().getUser().getId() && !isAdmin && !isManager && !isBuhgalter){
            String text = "Вы не можете редактировать отчет другого пользователя";
            model.addAttribute("text", text);
            return "redirect:/reportsManaging";
        }


        int newPos = currentPoint.getPosition() + 1;
        List<AdressesCoordinates> allAdresses = this.adressesCoordinatesService.getAllAdresssesWithOfficePriority();
        model.addAttribute("newPoint", new Point());
        model.addAttribute("departure", currentPoint.getArrival());
        model.addAttribute("allAdresses", allAdresses);
        model.addAttribute("adress", new AdressesCoordinates());
        model.addAttribute("day", currentPoint.getDay());
        model.addAttribute("newPointPos", newPos);
        return "addMediumPoint";
    }




    @RequestMapping(value = "/addTheMediumPoint/{dayId}/save", method = RequestMethod.POST)
    public String addMediumPoint(Model model, @PathVariable("dayId") long dayId
            , @ModelAttribute("newPoint") Point point
//            , @ModelAttribute("prevPoint") Point prevPoint
            , @ModelAttribute("departure") String departureAdr){

//        System.out.println(point.getPosition());

        Day day = this.dayService.findByIdDay(dayId);


        AdressesCoordinates departure = this.adressesCoordinatesService.findByIdAdressCoordinates(point.getDeparture().getId());
        AdressesCoordinates arrival = this.adressesCoordinatesService.findByIdAdressCoordinates(point.getArrival().getId());

        point.setDay(day);


        if (departure.getAddress() == null || arrival.getAddress() == null || departure.getAddress().isEmpty() || arrival.getAddress().isEmpty()){
            model.addAttribute("text", "Вы не можете добавить строку с пустым адресом");
            return "redirect:/addtheroute/" + dayId;
        }


        if(departure != null && arrival != null) {

            point.setDeparture(departure);
            point.setArrival(arrival);

            Integer pointDistance = this.pointService.knowTheDistance(point);
            point.setDistance(pointDistance);

            String accouncyType = day.getReport().getUser().getAccountancyType();
            if(accouncyType != null && accouncyType.equals("зональная")){
                int numberOfZoneArr = arrival.getNumberOfZone();
                int costByZoneArr = this.costByZoneService.findCostByZoneByNumberOfZone(numberOfZoneArr);
                this.pointService.calculationCostByZone(point, this.dayService.allPointsByDay(day).size(), costByZoneArr);
            }
        }

        this.pointService.addMediumPoint(point);


        return "redirect:/editDay/" + dayId;
    }


}
