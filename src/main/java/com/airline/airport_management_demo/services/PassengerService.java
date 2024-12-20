package com.airline.airport_management_demo.services;

import com.airline.airport_management_demo.entities.Passenger;
import com.airline.airport_management_demo.reporitories.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PassengerService {

    @Autowired
    private PassengerRepository passengerRepository;

    public Passenger createPassenger(Passenger passenger) {
        return passengerRepository.save(passenger);
    }

    public Optional<Passenger> getPassengerById(Long id) {
        return passengerRepository.findById(id);
    }

    public List<Passenger> getAllPassengers() {
        return passengerRepository.findAll();
    }

    public Passenger updatePassenger(Long id, Passenger passengerDetails) {
        Passenger passenger = passengerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Passenger not found"));
        passenger.setFirstName(passengerDetails.getFirstName());
        passenger.setLastName(passengerDetails.getLastName());
        passenger.setEmail(passengerDetails.getEmail());
        passenger.setPhoneNumber(passengerDetails.getPhoneNumber());
        passenger.setPassportNumber(passengerDetails.getPassportNumber());
        return passengerRepository.save(passenger);
    }

    public void deletePassenger(Long id) {
        passengerRepository.deleteById(id);
    }

}
