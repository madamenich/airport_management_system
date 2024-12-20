package com.airline.airport_management_demo.controllers;

import com.airline.airport_management_demo.entities.Airport;
import com.airline.airport_management_demo.services.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/airports")
public class AirportController {

    @Autowired
    private AirportService airportService;

    @GetMapping
    public String listAirports(Model model) {
        model.addAttribute("airports", airportService.getAllAirports());
        return "airports";
    }


    // Show Create Form
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("airport", new Airport()); // Empty object for new airport
        return "airport-form";
    }

    // Handle Create Form Submission
    @PostMapping
    public String createAirport(@ModelAttribute Airport airport, Model model) {
        try {
            airportService.createAirport(airport);
            return "redirect:/airports"; // Redirect to list
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("airport", airport);
            return "airport-form"; // Return to form with error
        }
    }

    // Show Edit Form
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Airport airport = airportService.getAirportById(id)
                .orElseThrow(() -> new RuntimeException("Airport not found"));
        model.addAttribute("airport", airport); // Pre-fill form with airport data
        return "airport-form";
    }

    // Handle Edit Form Submission
    @PostMapping("/{id}")
    public String updateAirport(@PathVariable Long id, @ModelAttribute Airport airport, Model model) {
        try {
            airportService.updateAirport(id, airport);
            return "redirect:/airports"; // Redirect to list
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("airport", airport);
            return "airport-form"; // Return to form with error

        }
    }

    @GetMapping("/delete/{id}")
    public String deleteAirport(@PathVariable Long id) {
        airportService.deleteAirport(id);
        return "redirect:/airports";
    }

}