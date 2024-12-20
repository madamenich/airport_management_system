package com.airline.airport_management_demo.services;

import com.airline.airport_management_demo.entities.Airport;
import com.airline.airport_management_demo.entities.Flight;
import com.airline.airport_management_demo.reporitories.AirportRepository;
import com.airline.airport_management_demo.reporitories.FlightRepository;
import com.airline.airport_management_demo.utilities.FlightRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class FlightService {

    @Autowired
    private FlightRepository flightRepository;
    @Autowired
    private AirportRepository airportRepository;

    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    public Optional<Flight> getFlightById(Long id) {
        return flightRepository.findById(id);
    }

    public Flight createFlight(FlightRequestDTO flightRequest) {
        // Fetch departure and arrival airports
        Airport departureAirport = airportRepository.findById(flightRequest.getDepartureAirportId())
                .orElseThrow(() -> new RuntimeException("Departure airport not found with ID: " + flightRequest.getDepartureAirportId()));

        Airport arrivalAirport = airportRepository.findById(flightRequest.getArrivalAirportId())
                .orElseThrow(() -> new RuntimeException("Arrival airport not found with ID: " + flightRequest.getArrivalAirportId()));

        // Map DTO to Entity
        Flight flight = new Flight();
        flight.setFlightNumber(flightRequest.getFlightNumber());
        flight.setDepartureTime(LocalDateTime.parse(flightRequest.getDepartureTime())); // Convert ISO-8601 string to LocalDateTime
        flight.setArrivalTime(LocalDateTime.parse(flightRequest.getArrivalTime()));
        flight.setDepartureAirport(departureAirport);
        flight.setArrivalAirport(arrivalAirport);

        return flightRepository.save(flight);
    }

    public Flight updateFlight(Long id, Flight flightDetails) {
        Flight flight = flightRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Flight not found with ID: " + id));
        flight.setFlightNumber(flightDetails.getFlightNumber());
        flight.setDepartureTime(flightDetails.getDepartureTime());
        flight.setArrivalTime(flightDetails.getArrivalTime());
        flight.setDepartureAirport(flightDetails.getDepartureAirport());
        flight.setArrivalAirport(flightDetails.getArrivalAirport());
        return flightRepository.save(flight);
    }

    public void deleteFlight(Long id) {
        Flight flight = flightRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Flight not found with ID: " + id));
        flightRepository.delete(flight);
    }

    public List<Flight> searchFlights(String flightNumber, String departureAirportName, String arrivalAirportName) {
        return flightRepository.searchFlights(flightNumber, departureAirportName, arrivalAirportName);
    }
}