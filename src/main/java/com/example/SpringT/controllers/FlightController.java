package com.example.SpringT.controllers;

import com.example.SpringT.Service.TicketService;
import com.example.SpringT.models.City;
import com.example.SpringT.models.Flight;
import com.example.SpringT.models.Seat;
import com.example.SpringT.models.Ticket;
import com.example.SpringT.repo.CityRepository;
import com.example.SpringT.repo.FlightRepository;
import com.example.SpringT.repo.SeatRepository;
import com.example.SpringT.repo.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/flights")
public class FlightController {

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private CityRepository cityRepository;

    @GetMapping("/select")
    public List<City> getAllCities() {
        return cityRepository.findAll();
    }

    @GetMapping("/available")
    public List<Flight> getAvailableFlights(@RequestParam Long fromCityId, @RequestParam Long toCityId) {
        return flightRepository.findByFromCityIdAndToCityId(fromCityId, toCityId);
    }


}
