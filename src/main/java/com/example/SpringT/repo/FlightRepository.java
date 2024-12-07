package com.example.SpringT.repo;

import com.example.SpringT.models.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Long> {
    List<Flight> findByFromCityIdAndToCityId(Long fromCityId, Long toCityId);
}