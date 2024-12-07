package com.example.SpringT.Service;

import com.example.SpringT.models.Flight;
import com.example.SpringT.models.Seat;
import com.example.SpringT.models.Ticket;
import com.example.SpringT.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;

@Service
public class TicketService {
    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private FlightRepository flightRepository;

    @Transactional
    public Ticket createTicket(Long flightId, int row, int seatNumber) {
        // Find the seat
        Seat seat = seatRepository.findByFlightIdAndRowAndSeatNumber(flightId, row, seatNumber);

        // Check if seat is available
        if (seat == null || seat.isBooked()) {
            throw new RuntimeException("Seat is not available");
        }

        // Find the flight
        Flight flight = flightRepository.findById(flightId)
                .orElseThrow(() -> new RuntimeException("Flight not found"));

        // Create ticket
        Ticket ticket = new Ticket();
        ticket.setFlight(flight);
        ticket.setSeat(seat);
        ticket.setPrice(BigDecimal.valueOf(10)); // Implement price calculation logic

        // Mark seat as booked
        seat.setBooked(true);
        seatRepository.save(seat);

        return ticketRepository.save(ticket);
    }


}