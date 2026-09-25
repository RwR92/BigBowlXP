package com.example.BigBowlProjekt.service;

import org.springframework.stereotype.Service;

@Service
public class LoginService {


    public String login(String username, String password){

        if(username.equals("admin") && password.equals("admin")){
            return "userpage/admin";
        } else if (username.equals("employee") && password.equals("employee")){
            return "userpage/employee";
        } else return "login";
    }
}
