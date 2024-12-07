package com.example.SpringT.controllers;

import com.example.SpringT.models.Seat;
import com.example.SpringT.repo.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seats")
public class SeatController {

    @Autowired
    private SeatRepository seatRepository;

    @PostMapping("/book")
    public String bookSeat(@RequestParam Long flightId, @RequestParam int row, @RequestParam int seatNumber) {
        Seat seat = seatRepository.findByFlightIdAndRowAndSeatNumber(flightId, row, seatNumber);
        if (seat != null && !seat.isBooked()) {
            seat.setBooked(true);
            seatRepository.save(seat);
            return "Seat booked successfully!";
        } else {
            return "Seat is already booked!";
        }
    }

    @GetMapping("/view")
    public List<Seat> getSeatsByFlight(@RequestParam Long flightId) {
        return seatRepository.findByFlightId(flightId);
    }
}
