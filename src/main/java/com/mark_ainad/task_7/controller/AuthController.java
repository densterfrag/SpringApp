package com.mark_ainad.task_7.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AuthController {
    @GetMapping("/login")
    public String login() {
        return "login";
    }
    @GetMapping("")
    public String GOLOGIN() {
        return "redirect:/login";
    }
}
