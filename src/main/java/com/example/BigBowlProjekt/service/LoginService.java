package com.example.BigBowlProjekt.service;

import com.example.BigBowlProjekt.dto.LoginInfoDTO;
import com.example.BigBowlProjekt.dto.UserTypeDTO;
import org.springframework.stereotype.Service;

@Service
public class LoginService {


    public UserTypeDTO login(LoginInfoDTO loginInfo){
        System.out.println("Logininformationer "+loginInfo.username()+" "+loginInfo.password());
        if(loginInfo.username().equals("admin") && loginInfo.password().equals("admin")){
            return new UserTypeDTO("admin");
        } else if (loginInfo.username().equals("employee") && loginInfo.password().equals("employee")) {
            return  new UserTypeDTO("employee");
        } else {
            return null;
        }
    }
}
