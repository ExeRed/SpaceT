package com.example.SpringT.Service;

import com.example.SpringT.models.Flight;
import com.example.SpringT.models.Seat;
import com.example.SpringT.models.Ticket;
import com.example.SpringT.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
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
    private JavaMailSender mailSender;

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

    public Ticket findByReturnToken(String returnToken) {
        return ticketRepository.findByReturnToken(returnToken)
                .orElseThrow(() -> new NullPointerException("ticket not found"));
    }

    public void cancelTicket(String returnToken) {
        Ticket ticket = findByReturnToken(returnToken);
        ticket.getSeat().setBooked(false);
        ticketRepository.delete(ticket);
    }


    public Ticket bookTicket(Long flightId, int row, int seatNumber, String email) {
        // Fetch the flight first
        Flight flight = flightRepository.findById(flightId)
                .orElseThrow(() -> new RuntimeException("Flight not found"));

        // Find the specific seat
        Seat seat = seatRepository.findByFlightIdAndRowAndSeatNumber(flightId, row, seatNumber);

        if (seat.isBooked()) {
            throw new RuntimeException("Seat is already booked");
        }

        // Create ticket and explicitly set the flight
        Ticket ticket = new Ticket();
        ticket.setFlight(flight);  // This is crucial - directly set the flight
        ticket.setSeat(seat);
        ticket.setEmail(email);
        ticket.setPrice(BigDecimal.valueOf(10));

        // Save ticket
        ticket = ticketRepository.save(ticket);

        // Mark seat as booked
        seat.setBooked(true);
        seatRepository.save(seat);

        sendTicketConfirmationEmail(ticket);

        return ticket;
    }

    private void sendTicketConfirmationEmail(Ticket ticket) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("flyservice@mail.com");
        message.setTo(ticket.getEmail());
        message.setSubject("Your Flight Ticket Confirmation");
        message.setText("Dear Passenger,\n\n" +
                "Your ticket has been booked successfully!\n" +
                "Flight: " + ticket.getFlight().getFromCity().getName() + " to " + ticket.getFlight().getToCity().getName() + "\n" +
                "Seat: " + ticket.getSeat().getRow() + "-" + ticket.getSeat().getSeatNumber() + "\n" +
                "Ticket ID: " + ticket.getId() + "\n\n" +
                "Ticket Token: " + ticket.getReturnToken() + "\n\n" +
                "Thank you for your booking!");

        mailSender.send(message);
    }
}