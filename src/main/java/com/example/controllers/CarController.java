package com.example.controllers;

import com.example.entities.Car;
import com.example.repositories.CarRepository;
import com.example.security.MyUserDetails;
import com.example.services.CarService;
import com.example.services.UserService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cars")
public class CarController {
    @Autowired
    private Gson gson;
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private CarService carService;
    @Autowired
    private UserService userService;

    @GetMapping
    @ResponseBody
    public Iterable<Car> getAllCars() {
        return carService.findAll();
    }

    @GetMapping("/available")
    @ResponseBody
    public Iterable<Car> getAllAvailableCars() {
        return carService.findAllAvailable();
    }

    @PostMapping
    public Car createCar(@RequestBody Car car) {
        return carRepository.save(car);
    }

    @GetMapping("/map")
    public String getMap(Model model, @AuthenticationPrincipal MyUserDetails myUserDetails)
    {
        Long id = myUserDetails.getUser().getId();
        Iterable<Car> carList = carService.findAllAvailable();
        String carListJson = gson.toJson(carList); // Преобразование в JSON model.addAttribute("carListJson", carListJson); return "carMap";
        System.out.println(carListJson);
        model.addAttribute("carListJson", carListJson);
        model.addAttribute("user", userService.findUserById(id));
        System.out.println(userService.findUserById(id));
        return "map"; // Возвращает шаблон "map.html"
    }

    // другие методы (например, update и delete)
}
