package com.airline.airport_management_demo.reporitories;

import com.airline.airport_management_demo.entities.Flight;
import com.airline.airport_management_demo.entities.Passenger;
import com.airline.airport_management_demo.entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    // Custom query to find tickets by passenger
    List<Ticket> findByPassenger(Passenger passenger);

    // Custom query to find tickets by flight
    List<Ticket> findByFlight(Flight flight);

    // Custom query to find a ticket by ticket number
    Ticket findByTicketNumber(String ticketNumber);
}

