package com.airline.airport_management_demo.controllers;

import com.airline.airport_management_demo.services.FlightService;
import com.airline.airport_management_demo.services.PassengerService;
import com.airline.airport_management_demo.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @Autowired
    private FlightService flightService;

    @Autowired
    private PassengerService passengerService;

    @Autowired
    private TicketService ticketService;

    @GetMapping("/dashboard")
    public String showDashboard(Model model) {
        model.addAttribute("totalFlights", flightService.getAllFlights().size());
        model.addAttribute("totalPassengers", passengerService.getAllPassengers().size());
        model.addAttribute("ticketsSold", ticketService.getAllTickets().size());
        return "dashboard";
    }
}
