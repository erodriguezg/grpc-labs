package com.github.erodriguezg.grpcjavalab.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {

    @GetMapping
    public String irLogin() {
        return "login/login.html";
    }

    @GetMapping("/olvido-password")
    public String irOlvidoPassword() {
        return "login/olvido-password.html";
    }

}
