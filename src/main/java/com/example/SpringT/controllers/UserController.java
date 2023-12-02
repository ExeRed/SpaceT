package com.example.SpringT.controllers;

import com.example.SpringT.models.Role;
import com.example.SpringT.controllers.MainController;
import com.example.SpringT.models.Seat;
import com.example.SpringT.models.Statistics;
import com.example.SpringT.models.User;
import com.example.SpringT.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('ADMIN')")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MainController mainController;


    @GetMapping
    public String userList(Model model) {
        Statistics stats = MainController.getStatistics();
        List<Seat> seats = MainController.getBooked_seat();
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("stats", stats);
        model.addAttribute("seat", seats);
        return "adminPage";
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
