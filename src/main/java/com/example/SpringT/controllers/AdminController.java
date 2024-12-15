package com.example.SpringT.controllers;

import com.example.SpringT.models.*;
//import com.example.SpringT.controllers.MainController;
import com.example.SpringT.repo.TicketRepository;
import com.example.SpringT.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {
    @Autowired
    private UserRepository userRepository;

    private final TicketRepository ticketRepository;

    @Autowired
    public AdminController(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @GetMapping
    public String adminPage(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "adminPage";
    }


    @GetMapping("/delete/{token}")
    public String deleteTicket(@PathVariable String token) {
    //    ticketRepository.deleteByToken(token);
        return "redirect:/admin";
    }

    @GetMapping("{user}")
    public String userEditForm(@PathVariable User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "userEdit";
    }

    @PostMapping
    public String userSave(
            @RequestParam String username,
            @RequestParam("userId") User user,
            @RequestParam(value = "roles", required = false) List<String> roles
    ) {
        user.setUsername(username);

        if (roles != null) {
            Set<Role> userRoles = Arrays.stream(Role.values())
                    .filter(role -> roles.contains(role.name()))
                    .collect(Collectors.toSet());

            user.setRoles(userRoles);
        } else {
            user.setRoles(Collections.emptySet());
        }

        userRepository.save(user);

        return "redirect:/admin";
    }

}
