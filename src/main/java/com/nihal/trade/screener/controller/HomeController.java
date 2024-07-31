package com.nihal.trade.screener.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    
    @GetMapping("/home")
    public String home() {
        return "home.html"; // This assumes the HTML file is named index.html
    }

}
