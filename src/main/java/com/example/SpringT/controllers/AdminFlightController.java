package com.example.SpringT.controllers;

import com.example.SpringT.models.City;
import com.example.SpringT.models.Flight;
import com.example.SpringT.models.Seat;
import com.example.SpringT.repo.CityRepository;
import com.example.SpringT.repo.FlightRepository;
import com.example.SpringT.repo.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin/flights")
public class AdminFlightController {

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private SeatRepository seatRepository;

    // Получение всех рейсов
    @GetMapping
    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    // Добавление нового рейса
    @PostMapping("/add")
    public ResponseEntity<Flight> addFlight(@RequestBody Flight flight) {
        // Проверяем, существуют ли города
        City fromCity = cityRepository.findById(flight.getFromCity().getId())
                .orElseThrow(() -> new RuntimeException("From city not found"));
        City toCity = cityRepository.findById(flight.getToCity().getId())
                .orElseThrow(() -> new RuntimeException("To city not found"));

        flight.setFromCity(fromCity);
        flight.setToCity(toCity);

        // Сохраняем рейс
        Flight savedFlight = flightRepository.save(flight);

        // Создаем места для рейса
        createSeatsForFlight(savedFlight);

        return ResponseEntity.ok(savedFlight);
    }

    // Метод для создания мест для рейса
    private void createSeatsForFlight(Flight flight) {
        // Используем количество мест из объекта рейса
        int totalSeats = flight.getTotalSeats(); // Предполагаем, что в модели Flight есть поле totalSeats
        int seatsPerRow = 6; // Можно сделать это также параметром
        int rows = (int) Math.ceil((double) totalSeats / seatsPerRow);

        List<Seat> seats = new ArrayList<>();
        for (int row = 1; row <= rows; row++) {
            for (int seatNum = 1; seatNum <= seatsPerRow && seats.size() < totalSeats; seatNum++) {
                Seat seat = new Seat();
                seat.setRow(row);
                seat.setSeatNumber(seatNum);
                seat.setBooked(false);
                seat.setFlight(flight);
                seats.add(seat);
            }
        }
        seatRepository.saveAll(seats);
    }

    // Удаление рейса
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteFlight(@PathVariable Long id) {
        Flight flight = flightRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Flight not found"));

        flightRepository.delete(flight);
        return ResponseEntity.ok().build();
    }
}