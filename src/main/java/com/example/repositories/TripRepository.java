package com.example.repositories;

import com.example.entities.Car;
import com.example.entities.Trip;
import com.example.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {
    Trip findTripById(Long id);
    List<Trip> findAllByUser(User user);
}
