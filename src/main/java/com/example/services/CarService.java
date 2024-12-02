package com.example.services;

import com.example.entities.Car;
import com.example.entities.User;
import com.example.repositories.CarRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class CarService {

    private CarRepository carRepository;

    public Iterable<Car> findAll()
    {
        return carRepository.findAll();
    }

    public Iterable<Car> findAllAvailable() { return carRepository.findByAvailableTrue(); }
    public Car findCarById(Long id)
    {
        return carRepository.findCarById(id);
    }
}
