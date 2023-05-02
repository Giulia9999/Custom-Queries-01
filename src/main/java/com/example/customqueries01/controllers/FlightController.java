package com.example.customqueries01.controllers;

import com.example.customqueries01.entities.Flight;
import com.example.customqueries01.entities.flightStatusEnum;
import com.example.customqueries01.repo.FlightRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/flights")
public class FlightController {
    @Autowired
    FlightRepo flightRepo;
    private static final int flightsNum = 50;
    @GetMapping
    public Collection<Flight> getAllFlight(){
        return flightRepo.findAll();
    }

    @PostMapping("/provision")
    public Collection<Flight> provisionFlights(){
        Random random = new Random();
        List<Flight> flightList = new ArrayList<>();
        for (int i= 0; i< flightsNum; i++){
            Flight flight = new Flight();
            flight.setDescription(generateRandomString(random, 10));
            flight.setFromAirport(generateRandomString(random, 5));
            flight.setToAirport(generateRandomString(random, 6));
            flight.setStatus(flightStatusEnum.ONTIME);
            flightRepo.save(flight);
            flightList.add(flight);
        }
        return flightList;
    }

    private String generateRandomString(Random random, int length){
        return random.ints(97,123)
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}
