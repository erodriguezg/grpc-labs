package com.github.erodriguezg.grpcjavalab.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registrarse")
public class RegistrarseController {

    @GetMapping
    public String irRegistrarse() {
        return "registrarse/registrarse.html";
    }

}
