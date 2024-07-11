package com.jjang051.gallery.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping({"/","/index","/home"})
    public String home() {
        return "index/index";
    }
}
