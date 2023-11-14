package com.example.SpringT.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HtmlController {
    @GetMapping("/footer.html")
    public String getFooter() {
        return "footer";
    }

    @GetMapping("/header.html")
    public String getHeader() {
        return "header";
    }
}
