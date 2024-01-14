package com.example.SpringT.controllers;

import com.example.SpringT.models.*;
import com.example.SpringT.controllers.MainController;
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

    @Autowired
    private MainController mainController;

    @Value("${upload.path}")
    private String uploadPath;

    private final TicketRepository ticketRepository;

    @Autowired
    public AdminController(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }


    // Display the user list along with statistics and ticket information
    @GetMapping
    public String userList(Model model) {
        Statistics stats = MainController.getStatistics();
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("stats", stats);
        model.addAttribute("tickets", ticketRepository.findAll());

        File[] files = new File(uploadPath).listFiles();
        if (files != null) {
            List<String> filenames = Arrays.stream(files)
                    .map(File::getName)
                    .collect(Collectors.toList());
            model.addAttribute("files", filenames);
        }

        return "adminPage";
    }

    // Handle file upload and redirect to the admin page
    @PostMapping("/addFile")
    public String addFile (@RequestParam("file") MultipartFile file) throws IOException {
        if (!file.isEmpty() || file.getSize() <= 3 * 1024 * 1024) {
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                boolean created = uploadDir.mkdirs();
                if (!created) {
                    System.err.println("Unable to create upload directory");
                }
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();

            try {
                file.transferTo(new File(uploadDir, resultFilename));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "redirect:/admin";
    }


    @GetMapping("/delete/{token}")
    public String deleteTicket(@PathVariable String token) {
        ticketRepository.deleteByToken(token);
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
