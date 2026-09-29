package com.example.BigBowlProjekt.controller;

public class HttpHelper {

    public static String authUser(String userRole) {
        if(userRole.isEmpty() || userRole == null){
            return null;
        } else if(userRole.equals("admin")){
            return "admin";
        }
        return "employee";
    }
}
