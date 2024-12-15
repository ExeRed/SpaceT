package com.example.SpringT.controllers;

import com.example.SpringT.Service.TicketService;
import com.example.SpringT.securities.SecurityException;
import com.example.SpringT.securities.SecurityException2;
import com.example.SpringT.models.*;
import com.example.SpringT.repo.EmailRepository;
import com.example.SpringT.repo.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Controller
@Component
public class MainController {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private EmailRepository emailRepository;

    @Value("${upload.path}")
    private String uploadPath;





    @PostMapping("/subscribe")
    @ResponseBody
    public void subscribeEmail(@RequestParam("email") String email) {
        Email emailObj = new Email(email);
        emailRepository.save(emailObj);
    }


    @PostMapping("/returned")
    public String ReturnedTicket(@ModelAttribute("token") String returnToken) {
        ticketService.cancelTicket(returnToken);
        return "returned-ticket";
    }

    @GetMapping("/blog")
    public String blog(Model model){
        File[] files = new File(uploadPath).listFiles();
        if (files != null) {
            List<String> filenames = Arrays.stream(files)
                    .map(File::getName)
                    .collect(Collectors.toList());
            model.addAttribute("files", filenames);
        }

        return "blog";
    }

    @RequestMapping("/aboutus")
    public String aboutus(){

        return "aboutus";
    }


    @GetMapping({"/", "/main"})
    public String main(){
        return "main";
    }

    @GetMapping("/returning")
    public String returnT() {
        return "returning";
    }



}