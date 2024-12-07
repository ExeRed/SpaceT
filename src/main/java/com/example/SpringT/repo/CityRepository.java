package com.example.SpringT.repo;


import com.example.SpringT.models.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Long> {
}