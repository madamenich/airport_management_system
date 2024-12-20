package com.airline.airport_management_demo.reporitories;

import com.airline.airport_management_demo.entities.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger, Long> {

    // Custom query to find a passenger by their email
    Passenger findByEmail(String email);

    // Custom query to find a passenger by their phone number
    Passenger findByPhoneNumber(String phoneNumber);
}

