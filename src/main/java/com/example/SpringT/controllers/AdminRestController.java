package com.example.SpringT.controllers;

import com.example.SpringT.models.Flight;
import com.example.SpringT.models.Seat;
import com.example.SpringT.repo.FlightRepository;
import com.example.SpringT.repo.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminRestController {

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private SeatRepository seatRepository;

    @GetMapping("/all-flights")
    public List<Map<String, Object>> getAllFlightsWithBookedSeats() {
        List<Flight> flights = flightRepository.findAll();
        List<Map<String, Object>> flightDetails = new ArrayList<>();

        for (Flight flight : flights) {
            Map<String, Object> flightInfo = new HashMap<>();
            flightInfo.put("flight", flight);

            // Get all seats for this flight
            List<Seat> seats = seatRepository.findByFlightId(flight.getId());

            // Get booked seats for this flight
            List<Seat> bookedSeats = seatRepository.findByFlightIdAndIsBookedTrue(flight.getId());

            flightInfo.put("totalSeats", seats.size());
            flightInfo.put("bookedSeats", bookedSeats.size());

            flightDetails.add(flightInfo);
        }

        return flightDetails;
    }
}