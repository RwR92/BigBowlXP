package com.example.BigBowlProjekt.controller;

import com.example.BigBowlProjekt.service.LoginService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PageController {

    private final LoginService loginService;

    public PageController(LoginService loginService){
        this.loginService = loginService;
    }

    @GetMapping
    public String loginPage(){

        return "login";
    }


    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password){
        return loginService.login(username, password);
    }
}
