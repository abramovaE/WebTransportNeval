package com.springapp.mvc.service;

import com.springapp.mvc.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

/**
 * Created by kot on 22.03.17.
 */

@Service
public class SecurityServiceImpl  implements SecurityService{

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsService userDetailsService;



    @Override
    public String findLoggedInusername() {
        Object userDetails = SecurityContextHolder.getContext().getAuthentication().getDetails();

        if(userDetails instanceof UserDetails){
            return ((UserDetails) userDetails).getUsername();
        }

        return null;
    }



    @Override
    public void autoLogin(String username, String password) {

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());

        authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        if(usernamePasswordAuthenticationToken.isAuthenticated()){
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }
    }


    public String findAutorizedUser(){
        Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(user instanceof org.springframework.security.core.userdetails.User){
            return ((org.springframework.security.core.userdetails.User) user).getUsername();
        }

        return null;
    }


}