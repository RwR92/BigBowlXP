package com.example.BigBowlProjekt.controller;

import com.example.BigBowlProjekt.dto.LoginInfoDTO;
import com.example.BigBowlProjekt.dto.UserTypeDTO;
import com.example.BigBowlProjekt.service.LoginService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PageController {

    private final LoginService loginService;

    public PageController(LoginService loginService){
        this.loginService = loginService;
    }

    /*@GetMapping
    public String loginPage(){

        return "login";
    } */


    @PostMapping("/login")
    public UserTypeDTO login(@RequestBody LoginInfoDTO loginInfo){
        return loginService.login(loginInfo);
    }
}
