package com.freerei.calculator.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class PublicPagesController {

    @GetMapping
    public String getIndex() {
        return "public/index";
    }

    @GetMapping("/terms")
    public String getTerms() {
        return "public/terms";
    }

    @GetMapping("/privacy")
    public String getPrivacyPolicy() {
        return "public/privacy";
    }

}
