package com.springapp.mvc.controller;

import com.springapp.mvc.model.Auto;
import com.springapp.mvc.model.User;
import com.springapp.mvc.service.*;
import com.springapp.mvc.validator.UserValidator;
import com.springapp.mvc.enums.RoleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by oem on 07.08.18.
 */
@Controller
public class MainController{




    protected boolean isAdmin;
    protected boolean isManager;
    protected boolean isBuhgalter;
    protected boolean isFinDir;
    protected User autorizedUser;


    private Map<String, String> topMenuButtons;



    static final ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
    static {
        messageSource.setBasename("validation");
        messageSource.setDefaultEncoding("UTF-8");
    }


    @Autowired
    protected ReportService reportService;

    @Autowired
    protected AdressesCoordinatesService adressesCoordinatesService;

    @Autowired
    protected DayService dayService;

    @Autowired
    protected PointService pointService;

    @Autowired
    protected SecurityService securityService;

    @Autowired
    protected UserService userService;

    @Autowired
    protected BlockedReportDataService blockedReportDataService;

    @Autowired
    protected CostByZoneService costByZoneService;

    @Autowired
    protected UserValidator userValidator;


    @Autowired
    protected UserRolesService userRolesService;

    @Autowired
    protected MainSettingsService mainSettingsService;

    @Autowired
    protected AutoService autoService;

    @Autowired
    protected DepService depService;


    public boolean isAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public boolean isManager() {
        return isManager;
    }

    public void setIsManager(boolean isManager) {
        this.isManager = isManager;
    }

    public boolean isBuhgalter() {
        return isBuhgalter;
    }

    public void setIsBuhgalter(boolean isBuhgalter) {
        this.isBuhgalter = isBuhgalter;
    }

    public boolean isFinDir() {
        return isFinDir;
    }

    public void setIsFinDir(boolean isFinDir) {
        this.isFinDir = isFinDir;
    }

    public User getAutorizedUser() {
        return autorizedUser;
    }

    public void setAutorizedUser(User autorizedUser) {
        this.autorizedUser = autorizedUser;
    }

    @ModelAttribute
    public void addAttributes(Model model, @ModelAttribute("text") String text){
        String name =  securityService.findAutorizedUser();
        if (name != null) {
            this.autorizedUser = this.userService.findByUsername(name);
            Map<RoleEnum, Boolean> userRolesMap = this.userService.getRolesMap(autorizedUser);

            this.isAdmin = userRolesMap.get(RoleEnum.ROLE_ADMIN);
            this.isBuhgalter = userRolesMap.get(RoleEnum.ROLE_BUHGALTER);
            this.isManager = userRolesMap.get(RoleEnum.ROLE_MANAGER);
            this.isFinDir = userRolesMap.get(RoleEnum.ROLE_FINDIR);

            model.addAttribute("isAdmin", isAdmin);
            model.addAttribute("isBuhgalter", isBuhgalter);
            model.addAttribute("isFinDir", isFinDir);
            model.addAttribute("isManager", isManager);
            model.addAttribute("autorizedUser", autorizedUser);

            if (text == null || text.isEmpty()) {
                model.addAttribute("text", null);
            } else {
                model.addAttribute("text", text);
            }
        }



    }



    @ModelAttribute
    public void getTopMenuButtons(Model model, HttpServletRequest request) {
        topMenuButtons = new LinkedHashMap<>();
        topMenuButtons.put("Выйти", "/login?logout");
        topMenuButtons.put("Назад", request.getHeader("referer"));
        topMenuButtons.put("O программе", "/info");
        topMenuButtons.put("В главное меню","/welcome");
        model.addAttribute("topMenu", topMenuButtons);

    }


    public Map<String, String> getTopMenuButtons() {
        return topMenuButtons;
    }


    public List<Auto> addUserAutosToModel(Model model, User user){
        List<Auto> userAutos = this.autoService.getAllAutosByUser(user);

        List<Auto> forDel = new ArrayList<>();
        for(Auto auto:userAutos){
            if(user.getCurrentAuto() != null && !user.getCurrentAuto().equals(auto) && this.autoService.hasClosedReports(auto)){
                forDel.add(auto);
            }
        }
        userAutos.removeAll(forDel);
        model.addAttribute("userAutos", userAutos);
        return userAutos;
    }

}
