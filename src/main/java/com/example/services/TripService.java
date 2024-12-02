package com.example.services;

import com.example.entities.Car;
import com.example.entities.Trip;
import com.example.entities.User;
import com.example.repositories.CarRepository;
import com.example.repositories.TripRepository;
import com.example.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
@Slf4j
public class TripService {
    @Autowired
    private TripRepository tripRepository;
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private UserRepository userRepository;

    private static final double MIN_LAT = 55.26919;
    private static final double MAX_LAT = 55.6298;
    private static final double MIN_LON = 37.1697;
    private static final double MAX_LON = 37.777;
    @Autowired
    private Random random;

    public Iterable<Trip> findAll()
    {
        return tripRepository.findAll();
    }

    public void save(Trip trip)
    {
        tripRepository.save(trip);
    }
    public Trip createTrip(Long carId, Long userId, LocalDateTime startTime) {
        Car car = carRepository.findCarById(carId);
        car.setAvailable(false);
        carRepository.save(car);
        User user = userRepository.findUserById(userId);
        Trip trip = new Trip();
        trip.setCar(car);
        trip.setUser(user);
        trip.setStartTime(startTime);
        return tripRepository.save(trip);
    }

    public BigDecimal calculateTotalCost(Trip trip) {
        LocalDateTime startTime = trip.getStartTime();
        LocalDateTime endTime = trip.getEndTime();
        long minutes = Duration.between(startTime, endTime).toMinutes();
        BigDecimal ratePerHour = trip.getCar().getPricing().getRatePerHour();
        return ratePerHour.multiply(BigDecimal.valueOf(minutes));
    }

    @Transactional
    public Trip endTrip(Long tripId) {
        Trip trip = tripRepository.findTripById(tripId);
        trip.setEndTime(LocalDateTime.now());
        trip.setCompleted(true);

        BigDecimal totalCost = calculateTotalCost(trip);
        trip.setTotalCost(totalCost);
        Car car = trip.getCar();
        double randomLat = car.getLatitude() + ((MAX_LAT - MIN_LAT) / 10) * random.nextDouble(0.5);
        double randomLon = car.getLongitude() + ((MAX_LON - MIN_LON) / 10) * random.nextDouble(0.5);
        car.setAvailable(true);
        car.setLongitude(randomLon);
        car.setLatitude(randomLat);
        carRepository.save(car);
        return tripRepository.save(trip);
    }

    public Trip getTripById(Long id) {
        return tripRepository.findTripById(id);
    }

    public List<Trip> getTripsByUser(User user) { return tripRepository.findAllByUser(user); }

}
