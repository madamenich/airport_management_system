package com.airline.airport_management_demo.controllers;

import com.airline.airport_management_demo.entities.Flight;
import com.airline.airport_management_demo.services.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping("/flights")
public class FlightController {

    @Autowired
    private FlightService flightService;

    @GetMapping
    public String listFlights(@RequestParam(value = "flightNumber", required = false) String flightNumber,
                              @RequestParam(value = "departureAirportName", required = false) String departureAirportName,
                              @RequestParam(value = "arrivalAirportName", required = false) String arrivalAirportName,
                              Model model) {
        List<Flight> flights = flightService.searchFlights(flightNumber, departureAirportName, arrivalAirportName);
        model.addAttribute("flights", flights);
        return "flight-list";
    }

    @GetMapping("/{id}")
    public String viewFlightDetails(@PathVariable Long id, Model model) {
        Flight flight = flightService.getFlightById(id)
                .orElseThrow(() -> new RuntimeException("Flight not found with ID: " + id));

        // Format departure and arrival times as strings
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        model.addAttribute("departureTime", flight.getDepartureTime().format(formatter));
        model.addAttribute("arrivalTime", flight.getArrivalTime().format(formatter));
        model.addAttribute("flight", flight);
        return "flight-details";
    }
}
