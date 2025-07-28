package com.github.erodriguezg.grpcjavalab.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String irIndex() {
        return "index/index";
    }

    @GetMapping("/example/datatable")
    public String irDataTablesExample() {
        return "index/tables.html";
    }

    @GetMapping("/example/charts")
    public String irChatsExample() {
        return "index/charts.html";
    }

    @GetMapping("/example/buttons")
    public String exampleButtons() {
        return "index/buttons.html";
    }

}
