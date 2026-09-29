package com.example.BigBowlProjekt.controller;

import com.example.BigBowlProjekt.dto.UserTypeDTO;


public class HttpHelper {

    public static String authUser(UserTypeDTO userRole) {
        if (userRole == null) {
            return null;
        }
        return userRole.userType();
    }
}
