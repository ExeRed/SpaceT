package com.example.SpringT.repo;


import com.example.SpringT.models.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat, Long> {
    Seat findByFlightIdAndRowAndSeatNumber(Long flightId, int row, int seatNumber);
    List<Seat> findByFlightId(Long flightId);
}
