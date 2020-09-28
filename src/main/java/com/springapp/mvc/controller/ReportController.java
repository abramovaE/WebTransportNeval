package com.springapp.mvc.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ibm.icu.text.Transliterator;
import com.springapp.mvc.Translit;
import com.springapp.mvc.emailWorker.SendEMail;
import com.springapp.mvc.enums.BodyTypes;
import com.springapp.mvc.excelWorker.ExcelParser;
import com.springapp.mvc.model.*;
import com.springapp.mvc.server.PostToServer;
import com.springapp.mvc.vspom.*;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import javax.mail.MessagingException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;

/**
 * Created by kot on 10.07.17.
 */

@Controller
public class ReportController extends MainController implements GetModelMap{



    private static final Logger logger = Logger.getLogger(ReportController.class.getName());
    private static final String LOAD_FROM_BILLING_URL = "https://bd.nevalink.net:4447/trips/";

    @RequestMapping(value = "/deleteReport/{reportId}")
    public String deleteReport(Model model, @PathVariable("reportId") long reportId){
        User reportUser = this.reportService.findByIdReport(reportId).getUser();
        if(autorizedUser.equals(reportUser)) {
            this.reportService.deleteReport(reportId);
            return "redirect:/reportsManaging";
        }

        else {
            model.addAttribute("text", "Вы не можете удалять отчет другого пользователя");
            return "redirect:/reportsManaging";
        }
    }


    @RequestMapping(value = {"/showReport/{reportId}", "/printReport/{reportId}"}, method = RequestMethod.GET)
    public String showReport(Model model, @PathVariable("reportId") long reportId, HttpServletRequest request){
        long st = System.currentTimeMillis();
        Report report = this.reportService.findByIdReport(reportId);
        List<Day> allDays = this.reportService.allDaysByReport(report);
        User user = report.getUser();

        System.out.println(user.getCurrentAuto().getBodyType());

        this.reportService.fillTheReport(report);
        report = this.reportService.getReportForShow(report);

        if(request.getRequestURI().contains("printReport")){
        getModelMap(model, report);
        return "printReport";

        }


        else  if (request.getRequestURI().contains("showReport")){
            if(!user.equals(autorizedUser) && !isAdmin && !isManager && !isBuhgalter && !isFinDir){
                model.addAttribute("text", "Вы не можете редактировать отчет другого пользователя");
                return "redirect:/reportsManaging";
            }

            else {
                if (report.isClosed()){
                    model.addAttribute("text", "Внимание! Этот отчет запрещено редактировать");
                }

                if(user.getBlocked()){
                    return "redirect:/welcome";
                }

                getTrips(report, allDays);
                getModelMap(model, report);
                YearMonth yearMonth = YearMonth.now();

                model.addAttribute("allDays", allDays);
                model.addAttribute("year", yearMonth.getYear());
                model.addAttribute("month", DateVspom.getMonthFromInt(yearMonth.getMonthValue()));
                model.addAttribute("monthsList", DateVspom.getAllMonthWithMonthPriority(report.getMonthNumber()));

                addUserAutosToModel(model, user);

                if(user.equals(autorizedUser) || isBuhgalter){
                    model.addAttribute("canDelete", true);
                }

                long end = System.currentTimeMillis();
                System.out.println("time " + (end - st));

                return "showReport";
            }
        }
        return "";
    }


    private void getTrips(Report report, List<Day> reportDays){

        User reportUser = report.getUser();
        YearMonth startYM = report.getYearMonth();

        YearMonth endYM = startYM.plusMonths(1);
        LocalDate localDateStart = LocalDate.from(startYM.atDay(1));
        LocalDate localDateEnd = LocalDate.from(endYM.atDay(1));

        if(reportUser.getDepartment() != null && reportUser.getDepartment().getLink() != null && !reportUser.getDepartment().getLink().isEmpty()){
            String loadUrl = LOAD_FROM_BILLING_URL + reportUser.getDepartment().getLink() + "/" + localDateStart + "/" + localDateEnd;
            String res = new PostToServer(loadUrl, 0, null).post();

//            String res = loadResForTest();

            if(res != null && !res.isEmpty()){
                JSONObject jsonObject = new JSONObject(res);
                GsonBuilder builder = new GsonBuilder();
                Gson gson = builder.create();
                DepartmentTrip[] departmentTrips = gson.fromJson(jsonObject.get("trips").toString(), DepartmentTrip[].class);

                Map<String, Map<String, List<DepartmentTrip>>> trips = new HashMap<>();
                if(departmentTrips != null && reportUser != null) {
                    for (DepartmentTrip departmentTrip : departmentTrips) {
                        String fio = departmentTrip.getF() + departmentTrip.getI() + departmentTrip.getO();
                        String date = departmentTrip.getDate();

                        Map<String, List<DepartmentTrip>> t;
                        if (trips.containsKey(fio)) {
                            t = trips.get(fio);
                            List<DepartmentTrip> list;
                            if(t.containsKey(date)){
                                list = t.get(date);
                                list.add(departmentTrip);
                            }
                            else {
                                list = new ArrayList<>();
                                list.add(departmentTrip);
                                t.put(date, list);
                            }
                        }

                        else {
                            t = new HashMap<>();
                            List<DepartmentTrip> list = new ArrayList<>();
                            list.add(departmentTrip);
                            t.put(date, list);
                            trips.put(fio, t);
                        }
                    }
                }

                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                String fio = reportUser.getSurname()+reportUser.getName()+reportUser.getPatronymic();

                for(Day day: reportDays){
                    LocalDate date = LocalDate.of(day.getReport().getYear(), day.getReport().getMonthNumber(), day.getDayNumber());
                    String dayDate = date.format(dateTimeFormatter);

                    if(trips.get(fio) != null){
                        List<DepartmentTrip> tripsByDate = trips.get(fio).get(dayDate);
                        int allPointsByDaySize = day.getPoints().size();

                        if(tripsByDate != null && tripsByDate.size() < allPointsByDaySize){
                            int r = allPointsByDaySize - tripsByDate.size();
                            for(int i =0; i< r; i++){
                                tripsByDate.add(new DepartmentTrip());
                            }
                        }
                        day.setDepartmentTrip(tripsByDate);
                    }

                    if(day.getSumm() != null && day.getSumm() != 0){
                        day.setPrevFillingDistance(this.reportService.getPrevFillingDist(reportDays, day));
                    }
                }
            }
        }
    }





    @RequestMapping(value = "/closeTheReport/{reportId}")
    public String closeTheReport(Model model, @PathVariable("reportId") long reportId) {
        Report report = this.reportService.findByIdReport(reportId);

        if(report.isClosed()){
        }

        else {
            StringBuilder stringBuilder = new StringBuilder("Редактирование отчета за " +
                    DateVspom.getPeriodFromYearMonth(report.getYearMonth()) + " пользователя " + report.getUser().getShortFullName());
            stringBuilder.append(" запрещено");
            this.reportService.closeTheReport(report);
            return  reportsByPeriodManaging(model, stringBuilder.toString(), report.getYear(), report.getMonthNumber());
        }
        return  reportsByPeriodManaging(model, null, report.getYear(), report.getMonthNumber());
    }



    @RequestMapping(value = "/openTheReport/{reportId}")
    public String openTheReport(Model model, @PathVariable("reportId") long reportId) {
        Report report = this.reportService.findByIdReport(reportId);
        if(isFinDir){
            if(report.isClosed()){
                this.reportService.openTheReport(report);
            }
        }
        return  reportsByPeriodManaging(model, null, report.getYear(), report.getMonthNumber());
    }



    @RequestMapping(value = {"/calculationFuelNorm/{userId}"}, method = RequestMethod.GET)
    public String calculationFuelNorm(Model model, @PathVariable("userId") long userId){
        User user = this.userService.findByIdUser(userId);
        if(user.equals(autorizedUser) || isManager || isFinDir || isAdmin){
            List<Koefficient> koefficients = user.getCurrentAuto().getKoefficients();
            model.addAttribute("koefficients", koefficients);
            model.addAttribute("user", user);
            return "calculationFuelNorm";
        }
        else {
            model.addAttribute("text", "доступ к данным другого пользователя запрещен");
            return "redirect:/welcome";
        }
    }


    @RequestMapping(value = "/setIsNeedChange/{pointId}")
    public String setColorRed(Model model,@PathVariable("pointId") long pointId){

        if(autorizedUser.getBlocked()){
            return "redirect:/welcome";
        }
        Point point = this.pointService.findByIdPoint(pointId);

        if(isManager || isAdmin) {
            point.setNeedChange(!point.isNeedChange());
            point.setIsChanged(false);
            this.pointService.updatePoint(point);
        }

        return "redirect:/showReport/"+point.getDay().getReport().getId();
    }


    @RequestMapping(value = "/makeXlsx/{reportId}")
    public String makeXlsx(Model model, @PathVariable("reportId") long reportId) {
        Report report = this.reportService.findByIdReport(reportId);
        this.reportService.fillTheReport(report);
        report = this.reportService.getReportForShow(report);
        if(report.isClosed()){
            this.reportService.updateReport(report);
        }

        String surname = report.getUser().getSurname() + report.getUser().getName().charAt(0) + report.getUser().getPatronymic().charAt(0);



        File reports = new File(Constants.MAIN_PATH  + "/reports/" + report.getYear() + "/" + report.getMonthNumber());
        if(!reports.exists()){
            reports.mkdirs();
        }

        String p = reports.getAbsolutePath() + "/" + report.getMonthNumber() + "-" + report.getYear() +"_" + surname + ".xlsx";
        Translit translit = new Translit();
        p = translit.transliterate(p);

        String text="Отчет отправлен на e-mail";
        User user = report.getUser();
        boolean isDirector = false;
        if(user.getPost().contains("директор")){
            isDirector = true;
        }
            if (report != null) {
                Path path = Paths.get(p);

                if(Files.exists(path)){
                    try {
                        Files.delete(path);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                File resultReport = new File(p);
                File source = new File(Constants.getResultReportForm());
                try {
                    Vspom.copyFile(source, resultReport);
                    ExcelParser excelParser = new ExcelParser();
                    excelParser.fillTheReport(resultReport.getAbsolutePath(),
                            report, reportService.getPreviousReport(report), isDirector,
                            this.reportService.allDaysByReport(report),
                            this.reportService.allDaysByReport(this.reportService.getPreviousReport(report)),
                            this.mainSettingsService.getCurrentMS(), report.getAuto());

                    SendEMail.sendMailWithAttachment(Constants.getAddressFrom(),
                            Constants.getPasswordFrom(),
                            autorizedUser.geteMail(),
                            "Транспортный отчет за " +
                                    DateVspom.getPeriodFromYearMonth(report.getYearMonth()) +
                                    ", водитель:  " + surname, resultReport.getAbsolutePath());
                    model.addAttribute("text", text);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (MessagingException e) {
                    text = "Отчет не был отправлен";
                }
            }

        return  reportsByPeriodManaging(model, text, report.getYear(), report.getMonthNumber());
    }




    @RequestMapping(value = {"/createAReport"}, method = RequestMethod.GET)
    public String createAReport(Model model) {
        if(!autorizedUser.getBlocked()){
            YearMonth yearMonth = YearMonth.now();
            model.addAttribute("report", new Report());
            model.addAttribute("user", autorizedUser);
            model.addAttribute("year", yearMonth.getYear());
            model.addAttribute("month", DateVspom.getMonthFromInt(yearMonth.getMonthValue()));
            model.addAttribute("monthsList", DateVspom.getAllMonthWithMonthPriority(0));
            addUserAutosToModel(model, autorizedUser);
            return "createAReport";
        }

        return "redirect:/welcome";
    }

    @RequestMapping(value = "/createAReport/saveTheReport", method = RequestMethod.POST)
    public String saveTheReportAction(Model model, @ModelAttribute("report") Report report) {

        if(autorizedUser.getBlocked()){
            return "redirect:/welcome";
        }
        String text;
        int month = DateVspom.getNumberOfMonth(report.getMonth());

        if(this.reportService.getReportByUserByYearByMonth(autorizedUser, report.getYear(), month) != null){
            text = "Отчет с таким периодом уже существует";
            model.addAttribute("text", text);
            return "redirect:/createAReport";
        }
        else {
            report.setMonthNumber(month);
            if (this.reportService.findByIdReport(report.getId()) == null) {
                report.setUser(autorizedUser);
                this.reportService.saveReport(report);
            } else {
                Report oldReport = this.reportService.findByIdReport(report.getId());
                oldReport.setYear(report.getYear());
                oldReport.setMonthNumber(DateVspom.getNumberOfMonth(report.getMonth()));
                oldReport.setAuto(this.autoService.findAutoById(report.getAuto().getId()));
                this.reportService.updateReport(oldReport);
            }
            model.addAttribute("newDay", new Day());
            return "redirect:/showReport/" + report.getId();
        }
    }


    @RequestMapping(value = {"/managerReportsManaging/{year}/{monthNumber}"}, method = RequestMethod.GET)
    public String reportsByPeriodManaging(Model model
            , @ModelAttribute("text") String text
            , @PathVariable("year") Integer year
            , @PathVariable("monthNumber") Integer monthNumber){
        YearMonth yearMonth = null;
//        try {
//            System.out.println("period " + period);
//            yearMonth = DateVspom.getYearMonthFromPeriod(new String(period.getBytes(), "UTF-8"));
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }

        yearMonth = YearMonth.of(year, monthNumber);
        List<Report> allReportsByPeriod = this.reportService.getAllReportsByYearByMonth(yearMonth.getYear(), yearMonth.getMonthValue());

        System.out.println(yearMonth);
        System.out.println("size "  + allReportsByPeriod.size());

        if(isBuhgalter){
            User shokin = this.userService.findByIdUser(35);
            User grigoriev = this.userService.findByIdUser(36);
            allReportsByPeriod.clear();
            allReportsByPeriod.add(this.reportService.getReportByUserByYearByMonth(shokin, yearMonth.getYear(), yearMonth.getMonthValue()));
            allReportsByPeriod.add(this.reportService.getReportByUserByYearByMonth(grigoriev, yearMonth.getYear(), yearMonth.getMonthValue()));
        }
        model.addAttribute("reportsList", allReportsByPeriod);
        model.addAttribute("report", new Report());
        model.addAttribute("period", DateVspom.getPeriodFromYearMonth(yearMonth));
        return "managerReportsManaging";
    }



    @RequestMapping(value = "/increase/{reportId}/{count}")
    public String increaseWeeks(Model model, @PathVariable("reportId") long reportId, @PathVariable("count") String count) {
        Report report = this.reportService.findByIdReport(reportId);

        switch (count){
            case "Ввести сумму вручную":
                return "addMobileSumm";


            default:
                report.setMobileWeeks(Integer.parseInt(count));
                if(!report.isClosed()) {
                    this.reportService.updateReport(report);
                }


                else {
                    model.addAttribute("text", "Этот отчет запрещено редакторовать");
                }

                int year = report.getYear();
                return "redirect:/mobile/" + year;

        }
    }


    public List<DepartmentTrip> getUserTrips(User reportUser, YearMonth startYM){
        List<DepartmentTrip> resultList = new ArrayList<>();
        YearMonth endYM = startYM.plusMonths(1);
        LocalDate localDateStart = LocalDate.from(startYM.atDay(1));
        LocalDate localDateEnd = LocalDate.from(endYM.atDay(1));

        if(reportUser.getDepartment() != null && reportUser.getDepartment().getLink() != null && !reportUser.getDepartment().getLink().isEmpty()){
            String loadUrl = LOAD_FROM_BILLING_URL + reportUser.getDepartment().getLink() + "/" + localDateStart + "/" + localDateEnd;
//            String res = new PostToServer(loadUrl, 0, null).post();

            String res = loadResForTest();


            if(res != null && !res.isEmpty()){
                JSONObject jsonObject = new JSONObject(res);
                GsonBuilder builder = new GsonBuilder();
                Gson gson = builder.create();

                DepartmentTrip[] departmentTrips = gson.fromJson(jsonObject.get("trips").toString(), DepartmentTrip[].class);
                if(departmentTrips != null && reportUser != null) {
                    for (DepartmentTrip departmentTrip : departmentTrips) {
                        if (departmentTrip != null && departmentTrip.getF() != null && reportUser.getSurname() != null &&
                                departmentTrip.getF().equals(reportUser.getSurname()) &&
                                departmentTrip.getI().equals(reportUser.getName()) &&
                                departmentTrip.getO().equals(reportUser.getPatronymic())) {
                            resultList.add(departmentTrip);
                        }
                    }
                }
            }
        }

        return resultList;
    }


    private String loadResForTest(){
        File file = new File("/home/oem/trips");
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));


            String s;
            while ((s = reader.readLine()) != null){
                sb.append(s);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sb.toString();
    }





    private void setDepTripToDays(List<DepartmentTrip> depTrips, List<Day> days){




        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        for(Day day: days){
            LocalDate date = LocalDate.of(day.getReport().getYear(), day.getReport().getMonthNumber(), day.getDayNumber());
            String dayDate = date.format(dateTimeFormatter);

            List<DepartmentTrip> tripsByDate = new ArrayList<>();

            for(DepartmentTrip departmentTrip: depTrips){
                if(departmentTrip.getDate().equals(dayDate)){
                    tripsByDate.add(departmentTrip);
                }
            }

            if(tripsByDate.size() < this.dayService.allPointsByDay(day).size()){
                int r = this.dayService.allPointsByDay(day).size() - tripsByDate.size();
                for(int i =0; i< r; i++){
                    tripsByDate.add(new DepartmentTrip());
                }
            }
            day.setDepartmentTrip(tripsByDate);
        }
    }


//    public List<DepartmentTrip> getUserTrips(User reportUser, YearMonth startYM){


    @Override
    public void getModelMap(Model model, Report report) {
        List<Day> allDays = this.reportService.allDaysByReport(report);
        model.addAttribute("report", report);
        model.addAttribute("allDays", allDays);
        model.addAttribute("day", new Day());
        model.addAttribute("point", new Point());
        model.addAttribute("user", report.getUser());

        if((report.getAuto() != null && report.getAuto().getBodyType() != null &&
                report.getAuto().getBodyType().equals(BodyTypes.MINIBUS.getName()))){
            model.addAttribute("shippingSumm", this.reportService.getShippingSumm(report)*500);
            model.addAttribute("shipping", true);
        }
        model.addAttribute("totalSum", this.reportService.getTotalSum(report));
        model.addAttribute("totalMobile", this.reportService.getTotalMobile(report));
        model.addAttribute("totalAmort", this.reportService.getTotalAmort(report));
    }
}