package com.example.BigBowlProjekt.controller;

import com.example.BigBowlProjekt.dto.LoginInfoDTO;
import com.example.BigBowlProjekt.dto.UserTypeDTO;
import com.example.BigBowlProjekt.service.LoginService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class PageController {

    private final LoginService loginService;

    public PageController(LoginService loginService) {
        this.loginService = loginService;
    }

    /*@GetMapping
    public String loginPage(){

        return "login";
    } */


    @PostMapping("/login")
    public UserTypeDTO login(@RequestBody LoginInfoDTO loginInfo, HttpSession session) {
        UserTypeDTO user = loginService.login(loginInfo);
        session.setAttribute("user", user);
        return user;
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpSession currsession) {
        HttpSession session = currsession;
        String userRole = session.getAttribute("user").toString();
        String user = HttpHelper.authUser(userRole);
        if (session != null) {
            session.invalidate();
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/auth")
    public ResponseEntity<UserTypeDTO> auth(HttpSession session) {
        UserTypeDTO user = (UserTypeDTO) session.getAttribute("user");

        return ResponseEntity.ok(user);
    }
}
