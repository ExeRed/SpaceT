package com.example.SpringT.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HtmlController {
    @GetMapping("/footer.html")
    public String getFooter() {
        return "footer";
    }

    @GetMapping("/rodo.html")
    public String getRodo() {
        return "rodo";
    }

    @GetMapping("/header.html")
    public String getHeader(Authentication authentication, Model model) {
        if (authentication != null && authentication.isAuthenticated()) {
            model.addAttribute("loggedIn", true);
        } else {
            model.addAttribute("loggedIn", false);
        }
        return "header";
    }

    @GetMapping("/flight-room.html")
    public String showFlightRoomPage(@RequestParam Long flightId, Model model) {
        model.addAttribute("flightId", flightId);
        return "flight-room";
    }

    @GetMapping("/ticket.html")
    public String showTicket(@RequestParam Long ticketId, Model model) {
        model.addAttribute("ticketId", ticketId);
        return "ticket";
    }
}
