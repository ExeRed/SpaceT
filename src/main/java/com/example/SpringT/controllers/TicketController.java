package com.example.SpringT.controllers;

import com.example.SpringT.Service.TicketService;
import com.example.SpringT.models.Ticket;
import com.example.SpringT.models.Token;
import com.example.SpringT.repo.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tickets")
public class TicketController {
    @Autowired
    private TicketService ticketService;
    @Autowired
    private TicketRepository ticketRepository;

    @PostMapping("/book")
    public ResponseEntity<?> bookTicket(
            @RequestParam Long flightId,
            @RequestParam int row,
            @RequestParam int seatNumber,
            @RequestParam String email
    ) {
        try {
            // Existing seat booking logic
            Ticket ticket = ticketService.bookTicket(flightId, row, seatNumber, email);
            return ResponseEntity.ok(ticket);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{ticketId}")
    public ResponseEntity<Ticket> getTicket(@PathVariable Long ticketId) {
        return ticketRepository.findById(ticketId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}