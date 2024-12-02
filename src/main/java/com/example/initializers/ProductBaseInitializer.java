package com.example.initializers;


import com.example.entities.Car;
import com.example.entities.Pricing;
import com.example.repositories.CarRepository;
import com.example.repositories.PricingRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ProductBaseInitializer {
    @Autowired
    private CarRepository carRepository;

    @Autowired
    private PricingRepository pricingRepository;

    @PostConstruct
    public void init() {
        Pricing pricing1 = new Pricing();
        pricing1.setName("Econom");
        pricing1.setRatePerHour(new BigDecimal(10.89));

        Pricing pricing2 = new Pricing();
        pricing2.setName("Comfort");
        pricing2.setRatePerHour(new BigDecimal(12));

        Pricing pricing3 = new Pricing();
        pricing3.setName("Premium");
        pricing3.setRatePerHour(new BigDecimal(14.50));

        pricingRepository.save(pricing1);
        pricingRepository.save(pricing2);
        pricingRepository.save(pricing3);


        Car car1 = new Car();
        car1.setId(1);
        car1.setMake("Toyota");
        car1.setLicensePlate("ABC123");
        car1.setModel("Camry");
        car1.setLatitude(55.7558);
        car1.setLongitude(37.6176);
        car1.setImageUrl("");
        car1.setAvailable(true);
        car1.setPricing(pricing1);

        Car car2 = new Car();
        car2.setId(2);
        car2.setMake("Honda");
        car2.setLicensePlate("DEF456");
        car2.setModel("Civic");
        car2.setLatitude(55.7522);
        car2.setLongitude(37.6156);
        car2.setAvailable(false);
        car2.setPricing(pricing1);

        Car car3 = new Car();
        car3.setId(3);
        car3.setMake("BMW");
        car3.setLicensePlate("GHI789");
        car3.setModel("X5");
        car3.setLatitude(55.7601);
        car3.setLongitude(37.6184);
        car3.setAvailable(false);
        car3.setPricing(pricing1);

        Car car4 = new Car();
        car4.setId(4);
        car4.setMake("Mercedes");
        car4.setLicensePlate("JKL012");
        car4.setModel("E-Class");
        car4.setLatitude(55.7650);
        car4.setLongitude(37.6050);
        car4.setAvailable(false);
        car4.setPricing(pricing1);

        Car car5 = new Car();
        car5.setId(5);
        car5.setMake("Audi");
        car5.setLicensePlate("MNO345");
        car5.setModel("A4");
        car5.setLatitude(55.7589);
        car5.setLongitude(37.6206);
        car5.setAvailable(false);
        car5.setPricing(pricing1);

        Car car6 = new Car();
        car6.setId(6);
        car6.setMake("Ford");
        car6.setLicensePlate("PQR678");
        car6.setModel("Focus");
        car6.setLatitude(55.7541);
        car6.setLongitude(37.6228);
        car6.setAvailable(false);
        car6.setPricing(pricing1);

        Car car7 = new Car();
        car7.setId(7);
        car7.setMake("Toyota");
        car7.setLicensePlate("О123МБ");
        car7.setModel("Camry");
        car7.setLatitude(55.7538);
        car7.setLongitude(37.6184);
        car7.setImageUrl("/static/images/camry.png");
        car7.setAvailable(true);
        car7.setPricing(pricing1);
        carRepository.save(car1);
        carRepository.save(car2);
        carRepository.save(car3);
        carRepository.save(car4);
        carRepository.save(car5);
        carRepository.save(car6);
        carRepository.save(car7);
    }
}
