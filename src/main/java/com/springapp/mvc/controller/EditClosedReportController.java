package com.springapp.mvc.controller;

import com.springapp.mvc.model.*;
import com.springapp.mvc.service.*;
import com.springapp.mvc.vspom.Vspom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by oem on 18.06.18.
 */

@Controller
public class EditClosedReportController extends MainController {



    @RequestMapping(value = "/editClosedReport/{reportId}", method = RequestMethod.GET)
    public String editClosedReport(Model model, @PathVariable("reportId") long reportId) {
        Report report = this.reportService.findByIdReport(reportId);
        BlockedReportData blockedReportData = report.getBlockedReportData();
        report = this.reportService.getReportForShow(report);

        model.addAttribute("report", report);

        model.addAttribute("blockedReportData", blockedReportData);
        List<Day> allDays = this.reportService.allDaysByReport(report);

        model.addAttribute("allDays", allDays);
        model.addAttribute("day", new Day());
        model.addAttribute("point", new Point());
        return  "editClosedReport";
    }


    @RequestMapping(value = "/editClosedReport/{reportId}/save", method = RequestMethod.POST)
    public String editClosedReportSave(Model model, @ModelAttribute("blockedReportData") BlockedReportData blockedReportData,
                                       @PathVariable("reportId") long reportId) {

        Report report = this.reportService.findByIdReport(reportId);
        blockedReportData.setReport(report);
        this.blockedReportDataService.update(blockedReportData);
        return "redirect:/editClosedReport/" + report.getId();
    }
}
