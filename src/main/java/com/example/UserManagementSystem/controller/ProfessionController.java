package com.example.UserManagementSystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/profession")
public class ProfessionController {
    @GetMapping("")
    public String showHome(){
        return "landing/profession";
    }
}
