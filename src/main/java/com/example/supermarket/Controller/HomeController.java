package com.example.supermarket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/homepage")
    public String homepage() {
        return "homepage"; // homepage.html
    }

    @GetMapping("/home")
    public String homeRedirect() {
        return "redirect:/homepage";
    }
}
