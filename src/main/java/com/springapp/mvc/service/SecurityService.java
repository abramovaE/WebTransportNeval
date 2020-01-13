package com.springapp.mvc.service;



public interface SecurityService {
    String findLoggedInusername();

    void autoLogin(String username, String password);

    String findAutorizedUser();

}
