package com.springapp.mvc.controller;

import com.springapp.mvc.model.Department;
import com.springapp.mvc.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by oem on 23.05.19.
 */

@Controller
public class DepManagingController extends MainController{

    @RequestMapping(value = "/depManaging")
    public String depMAnaging(Model model){
        model.addAttribute("depList", this.depService.getAllDepts());
        return "depManaging";
    }



    @RequestMapping(value = "/addDepartment", method = RequestMethod.GET)
    public String addDepartment(Model model){
        model.addAttribute("dep", new Department());
        model.addAttribute("title", "Создать новый отдел");
        List<User> freeUsers = new ArrayList<>();
        for(User user: this.userService.getAllUsers()){
            if(user.getDepartment() == null){
                freeUsers.add(user);
            }
        }
        model.addAttribute("usersList", freeUsers);
        return "addDepartment";
    }


    @RequestMapping(value = "/editDep/{depId}", method = RequestMethod.GET)
    public String editDep(Model model, @PathVariable("depId") long depId){
        Department department = this.depService.findDepById(depId);
        model.addAttribute("dep", department);
        model.addAttribute("title", "Редактировать отдел " + department.getName());

        List<User> freeUsers = new ArrayList<>();
        for(User user: this.userService.getAllUsers()){
            if(user.getDepartment() == null){
                freeUsers.add(user);
            }
        }
        model.addAttribute("usersList", freeUsers);

        return "addDepartment";
    }


    @RequestMapping(value = "/addDepartment/save",  method = RequestMethod.POST)
    public String addDepartmentSave(Model model, @ModelAttribute("dep") Department department){
        if(this.depService.findDepById(department.getId()) == null){
            this.depService.saveDep(department);
        }

        else {
            this.depService.updateDep(department);
        }

        if(department.getUsersId() != null && !department.getUsersId().isEmpty()) {
            for (int userId : department.getUsersId()) {
                User user = this.userService.findByIdUser(userId);
                user.setDepartment(department);
                this.userService.updateUser(user);
            }
        }
        return "redirect:/depManaging";
    }



    @RequestMapping(value = "/delUserFromDep/{userId}")
    public String delUserFromDep(Model model, @PathVariable("userId") long userId){
        User user = this.userService.findByIdUser(userId);
        user.setDepartment(null);
        this.userService.updateUser(user);
        return "redirect:/depManaging";
    }

    @RequestMapping(value = "/delDep/{depId}")
    public String delDep(Model model, @PathVariable("depId") long depId){
        Department department = this.depService.findDepById(depId);

        for(User user:department.getUsers()){
            user.setDepartment(null);
            this.userService.updateUser(user);
        }
        department.setUsers(null);

        this.depService.deleteDep(department);
            return "redirect:/depManaging";
    }

}
