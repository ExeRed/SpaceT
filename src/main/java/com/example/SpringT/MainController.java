package com.example.SpringT;

import com.example.SpringT.models.*;
import com.example.SpringT.repo.EmailRepository;
import com.example.SpringT.repo.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.example.SpringT.RocketRoom.seats;

@Controller
public class MainController {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private EmailRepository emailRepository;

    public static List<Seat> booked_seat = new ArrayList<>();
    public static List<UUID> booked_token = new ArrayList<>();
    public static List<Ticket> tickets = new ArrayList<>();


    public static void BookedToken() {
        for (int j = 0; j < 81; j++) {
            booked_token.add(j, null);
        }
    }

    public static void BookedSeat() {
        for (int i = 0; i < 81; i++) {
            booked_seat.add(i, new Seat(0,0,0));
        }
    }

    public static void tickets() {

    }

    @PostMapping("/subscribe")
    @ResponseBody
    public void subscribeEmail(@RequestParam("email") String email) {
        Email emailObj = new Email(email);
        emailRepository.save(emailObj);
    }


    @RequestMapping("/purchasing")
    public String purchase(){

        return "seat";
    }

    @RequestMapping("/aboutus")
    public String aboutus(){

        return "aboutus";
    }

    @GetMapping("/main")
    public String mainPage() {
        return "main";
    }

    @GetMapping("/returning")
    public String returnT() {
        return "returning";
    }

    Statistics statistics = new Statistics(0,81,0);
    @RequestMapping("/purchase")
    public String bookSeat(@RequestParam int row, @RequestParam int column) {

        Seat client = new Seat(row, column, 8);

        if ((row >= 0 && column >= 0) && (row <= 9 && column <= 9)) {

            Ticket ticket;
            BookedSeat();
            BookedToken();
            int index = row * 9 + column - 10;
            if (row < 5) {
                UUID uuid = UUID.randomUUID();
                ticket = new Ticket(uuid, new Seat(row, column, 10));
                booked_seat.set(index,new Seat(row, column, 10));
                booked_token.set(index, uuid);
            } else {
                UUID uuid = UUID.randomUUID();
                ticket = new Ticket(uuid, new Seat(row, column, 8 ));
                booked_seat.set(index, new Seat(row, column, 8));
                booked_token.set(index, uuid);
            }
            return getSeat(client, ticket, index);
        } else {
            throw new SecurityException("The number of a row or a column is out of bounds!");
        }
    }

    private String getSeat(Seat client, Ticket ticket, int index) {

        if (seats.get(client.getSeat_row() * 9 + client.getSeat_column() - 10).getSeat_row() == booked_seat.get(client.getSeat_row() * 9 + client.getSeat_column() - 10).getSeat_row() &&
                seats.get(client.getSeat_row() * 9 + client.getSeat_column() - 10).getSeat_column() == booked_seat.get(client.getSeat_row() * 9 + client.getSeat_column() - 10).getSeat_column()) {
            if (client.getSeat_row() < 5) {
                statistics.setCurrent_income(statistics.getCurrent_income() + 10);
            } else {
                statistics.setCurrent_income(statistics.getCurrent_income() + 8);
            }

            statistics.setNumber_of_available_seats(statistics.getNumber_of_available_seats() - 1);
            statistics.setNumber_of_purchased_tickets(statistics.getNumber_of_purchased_tickets() + 1);
            seats.set(client.getSeat_row() * 9 + client.getSeat_column() - 10, new Seat(0,0,0));
            ticketRepository.save(ticket);
            return "redirect:/ticket/" + index;
        } else {
            throw new SecurityException("The ticket has been already purchased!");
        }
    }

    @GetMapping("/ticket/{index}")
    public String showTicket(@PathVariable int index, Model model) {
        model.addAttribute("token", booked_token.get(index));
        model.addAttribute("row", booked_seat.get(index).getSeat_row());
        model.addAttribute("column", booked_seat.get(index).getSeat_column());
        model.addAttribute("price", booked_seat.get(index).getPrice());
        return "ticket";
    }




    @PostMapping("/returned")
    public String ReturnedTicket(@ModelAttribute("token") Token token, Model model) {
        UUID token1 = token.getToken();
        if (booked_token.contains(token1)) {
            int i = booked_token.indexOf(token1);
            ReturnedTicket returnedTicket = new ReturnedTicket(booked_seat.get(i));
            seats.set(i, booked_seat.get(i));
            if (booked_seat.get(i).getSeat_row() < 5) {
                statistics.setCurrent_income(statistics.getCurrent_income() - 10);
            } else {
                statistics.setCurrent_income(statistics.getCurrent_income() - 8);
            }
            statistics.setNumber_of_available_seats(statistics.getNumber_of_available_seats() + 1);
            statistics.setNumber_of_purchased_tickets(statistics.getNumber_of_purchased_tickets() - 1);
            booked_token.remove(token1);
            model.addAttribute("token", token1);
            model.addAttribute("row", returnedTicket.getReturned_ticket().getSeat_row());
            model.addAttribute("column", returnedTicket.getReturned_ticket().getSeat_column());
            ticketRepository.deleteByToken(token1);

            return "returned-ticket";
        } else {
            throw new SecurityException("Wrong token!");
        }
    }

    @GetMapping("/all-tickets")
    public String getAllTickets(Model model) {
        List<Ticket> allTickets = ticketRepository.findAll();
        model.addAttribute("tickets", allTickets);
        return "tickets-page";
    }
/*
    @PostMapping("/stats")
    public Statistics stats(@RequestParam (required = false) String password) {
        if (password == null) {
            throw new SecurityException2("The password is wrong!");
        } else if (password.equals("super_secret")) {
            return statistics;
        } else {
            throw new SecurityException("The password is wrong!");
        }
    } */

}