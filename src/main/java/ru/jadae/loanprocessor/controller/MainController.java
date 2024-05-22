package ru.jadae.loanprocessor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class MainController {

    @GetMapping("/main-page")
    public String mainPage(@RequestParam(name="name", required=false, defaultValue="me") String name,
                           Map<String, Object> model) {
        model.put("name", name);
        return "main-page";
    }

    @GetMapping("/loan-application")
    public String loanApplication() {
        return "loan-application";
    }

}