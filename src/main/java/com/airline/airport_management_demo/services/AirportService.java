package com.airline.airport_management_demo.services;

import aj.org.objectweb.asm.commons.Remapper;
import com.airline.airport_management_demo.entities.Airport;
import com.airline.airport_management_demo.reporitories.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AirportService {

    @Autowired
    private AirportRepository airportRepository;

    public Airport createAirport(Airport airport) {
        if (airportRepository.existsByName(airport.getName())) {
            throw new RuntimeException("Airport with name '" + airport.getName() + "' already exists.");
        }
        return airportRepository.save(airport);
    }

    public Optional<Airport> getAirportById(Long id) {
        return airportRepository.findById(id);
    }

    public List<Airport> getAllAirports() {
        return airportRepository.findAll();
    }

    public Airport updateAirport(Long id, Airport airportDetails) {
        // Fetch the existing record
        Airport existingAirport = airportRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Airport not found"));

        // Update only fields that are not null
        if (airportDetails.getName() != null) {
            // Check for duplicate names before updating
            if (airportRepository.existsByName(airportDetails.getName()) &&
                    !existingAirport.getName().equals(airportDetails.getName())) {
                throw new RuntimeException("Airport with name '" + airportDetails.getName() + "' already exists.");
            }
            existingAirport.setName(airportDetails.getName());
        }

        if (airportDetails.getCode() != null) {
            existingAirport.setCode(airportDetails.getCode());
        }

        if (airportDetails.getLocation() != null) {
            existingAirport.setLocation(airportDetails.getLocation());
        }

        if (airportDetails.getCountry() != null) {
            existingAirport.setCountry(airportDetails.getCountry());
        }

        // Save the updated entity
        return airportRepository.save(existingAirport);
    }

    public void deleteAirport(Long id) {
        if (!airportRepository.existsById(id)) {
            throw new RuntimeException("Airport not found with ID: " + id);
        }
        airportRepository.deleteById(id);
    }
}
