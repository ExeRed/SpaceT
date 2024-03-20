package com.example.SpringT.controllers;

import com.example.SpringT.models.RocketRoom;
import org.springframework.web.bind.annotation.GetMapping;

import static com.example.SpringT.models.RocketRoom.getAllseat;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    private final RocketRoom cinema;

    public RestController() {
        this.cinema = getAllseat(9, 9);
    }

    @GetMapping("/seats")
    public RocketRoom cinemaRoom() {
        return cinema;
    }

   /* Statistics statistics = new Statistics(0,81,0);
    @PostMapping("/purchase")
    public String bookSeat(@RequestBody Seat client) {

        if ((client.getRow() >= 0 && client.getColumn() >= 0) &&
                (client.getRow() <= 9 && client.getColumn() <= 9)) {

            Ticket ticket;
            Ticket.BookedSeat();
            Ticket.BookedToken();
            int index = client.getRow() * 9 + client.getColumn() - 10;
            if (client.getRow() < 5) {
                UUID uuid = UUID.randomUUID();
                ticket = new Ticket(uuid, new Seat(client.getRow(), client.getColumn(), 10));
                booked_seat.set(index,new Seat(client.getRow(), client.getColumn(), 10));
                booked_token.set(index, uuid);
            } else {
                UUID uuid = UUID.randomUUID();
                ticket = new Ticket(uuid, new Seat(client.getRow(), client.getColumn(), 8 ));
                booked_seat.set(index, new Seat(client.getRow(), client.getColumn(), 8));
                booked_token.set(index, uuid);
            }

            return "redirect:/ticket/" + index;
        } else {
            throw new SecurityException("The number of a row or a column is out of bounds!");
        }
    }

    private Ticket getSeat(@RequestBody Seat client, Ticket ticket) {
        if (seats.get(client.getRow() * 9 + client.getColumn() - 10).row == booked_seat.get(client.getRow() * 9 + client.getColumn() - 10).row &&
                seats.get(client.getRow() * 9 + client.getColumn() - 10).column == booked_seat.get(client.getRow() * 9 + client.getColumn() - 10).column) {
            if (client.getRow() < 5) {
                statistics.setCurrent_income(statistics.current_income + 10);
            } else {
                statistics.setCurrent_income(statistics.current_income + 8);
            }

            statistics.setNumber_of_available_seats(statistics.number_of_available_seats - 1);
            statistics.setNumber_of_purchased_tickets(statistics.number_of_purchased_tickets + 1);
            seats.set(client.getRow() * 9 + client.getColumn() - 10, new Seat(0,0,0));

            return ticket;
        } else {
            throw new SecurityException("The ticket has been already purchased!");
        }
    }

    @PostMapping("/return")
    public ReturnedTicket returnedTicket(@RequestBody Token token) {
        UUID token1 = token.getToken();
        if (booked_token.contains(token1)) {
            int i = booked_token.indexOf(token1);
            ReturnedTicket returnedTicket = new ReturnedTicket(booked_seat.get(i));
            seats.set(i, booked_seat.get(i));
            if (booked_seat.get(i).row < 5) {
                statistics.setCurrent_income(statistics.current_income - 10);
            } else {
                statistics.setCurrent_income(statistics.current_income - 8);
            }
            statistics.setNumber_of_available_seats(statistics.number_of_available_seats + 1);
            statistics.setNumber_of_purchased_tickets(statistics.number_of_purchased_tickets - 1);
            booked_token.remove(token1);
            return returnedTicket;
        } else {
            throw new SecurityException("Wrong token!");
        }
    }

    @PostMapping("/stats")
    public Statistics stats(@RequestParam(required = false) String password) {
        if (password == null) {
            throw new SecurityException2("The password is wrong!");
        } else if (password.equals("super_secret")) {
            return statistics;
        } else {
            throw new SecurityException("The password is wrong!");
        }
    } */

}
