package com.springapp.mvc.validator;

import com.springapp.mvc.model.User;
import com.springapp.mvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import javax.validation.ConstraintViolation;
import java.util.Set;

/**
 * Created by kot on 04.08.17.
 */

@Component
public class UserValidator implements Validator{



    @Autowired
    private UserService userService;


    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        User user = (User) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "login", "Required");

        if(user.getLogin().length() < 4 || user.getLogin().length() > 32){
            errors.rejectValue("login", "Size.userForm.login");
        }

        if(userService.findByUsername(user.getLogin()) != null){
            errors.rejectValue("login", "Duplicate.userForm.login");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "Required");

        if(user.getPassword().length() < 6 || user.getPassword().length() > 32){
            errors.rejectValue("password", "Size.userForm.password");
        }

        if(!user.getConfirmPassword().equals(user.getPassword())){

            errors.rejectValue("confirmPassword", "Different.userForm.password");
        }
    }

    public void validateChanges(Object o, Errors errors) {

        User user = (User) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "login", "Required");

//        if(user.getLogin().length() < 6 || user.getLogin().length() > 32){
//            errors.rejectValue("username", "Size.userForm.username");
//        }
//
//        if(userService.findByUsername(user.getLogin()) != null){
//            errors.rejectValue("username", "Duplicate.userForm.username");
//        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "Required");



        if(user.getPassword().length() < 6 || user.getPassword().length() > 32){
            errors.rejectValue("password", "Size.userForm.password");
        }

        if(!user.getConfirmPassword().equals(user.getPassword())){

            errors.rejectValue("confirmPassword", "Different.userForm.password");
        }
    }
}
