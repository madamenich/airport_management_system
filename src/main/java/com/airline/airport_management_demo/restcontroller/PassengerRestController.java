package com.airline.airport_management_demo.restcontroller;

import com.airline.airport_management_demo.entities.Passenger;
import com.airline.airport_management_demo.services.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/private/passengers")
public class PassengerRestController {

    @Autowired
    private PassengerService passengerService;

    @PostMapping
    public ResponseEntity<Passenger> createPassenger(@RequestBody Passenger passenger) {
        Passenger newPassenger = passengerService.createPassenger(passenger);
        return ResponseEntity.status(201).body(newPassenger); // Created
    }

    @GetMapping("/{id}")
    public ResponseEntity<Passenger> getPassengerById(@PathVariable Long id) {
        Optional<Passenger> passenger = passengerService.getPassengerById(id);
        return passenger.map(ResponseEntity::ok) // 200 OK
                .orElseGet(() -> ResponseEntity.status(404).build()); // Not Found
    }

    @GetMapping
    public ResponseEntity<List<Passenger>> getAllPassengers() {
        List<Passenger> passengers = passengerService.getAllPassengers();
        return ResponseEntity.ok(passengers); // 200 OK
    }

    @PutMapping("/{id}")
    public ResponseEntity<Passenger> updatePassenger(@PathVariable Long id, @RequestBody Passenger passengerDetails) {
        try {
            Passenger updatedPassenger = passengerService.updatePassenger(id, passengerDetails);
            return ResponseEntity.ok(updatedPassenger); // 200 OK
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).build(); // Not Found
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePassenger(@PathVariable Long id) {
        try {
            passengerService.deletePassenger(id);
            return ResponseEntity.noContent().build(); // 204 No Content
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).build(); // Not Found
        }
    }
}
