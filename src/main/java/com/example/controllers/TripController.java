package com.example.controllers;

import com.example.entities.Car;
import com.example.entities.Trip;
import com.example.repositories.TripRepository;
import com.example.security.MyUserDetails;
import com.example.services.EmailService;
import com.example.services.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@Controller
@RequestMapping("/trips")
public class TripController {
    @Autowired
    TripService tripService;
    @Autowired
    EmailService emailService;

    @GetMapping
    @ResponseBody
    public Iterable<Trip> getAllTrips() {
        return tripService.findAll();
    }

    @PostMapping
    public String createTrip(@RequestParam Long carId, @RequestParam Long userId) {
        LocalDateTime startTime = LocalDateTime.now();

        Trip trip = tripService.createTrip(carId, userId, startTime);
        System.out.println("Created trip ID: " + trip.getId());
        return "redirect:trips/" + trip.getId();
    }
    @PutMapping("/{id}/end")
    public ResponseEntity endTrip(@PathVariable Long id, Model model,
                                  @AuthenticationPrincipal MyUserDetails myUserDetails) {
        tripService.endTrip(id);
        Trip trip = tripService.getTripById(id);
        model.addAttribute("trip", trip);
        System.out.println(trip.getUser().getUsername());
        emailService.sendEmail(trip.getUser().getUsername(), "ManStyle - Каршеринг", "Здравствуйте, "
                + trip.getUser().getName() + "!" + "\n\nВаш поездка завершена.");
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public String getTripById(@PathVariable Long id, Model model) {
        Trip trip = tripService.getTripById(id);
        model.addAttribute("trip", trip);
        return "trip";
    }

}
