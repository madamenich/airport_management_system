package com.airline.airport_management_demo.reporitories;

import com.airline.airport_management_demo.entities.Airport;
import com.airline.airport_management_demo.entities.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {

    // Custom query to find flights by departure airport
    List<Flight> findByDepartureAirport(Airport departureAirport);

    // Custom query to find flights by arrival airport
    List<Flight> findByArrivalAirport(Airport arrivalAirport);

    // Custom query to find flights by flight number
    Flight findByFlightNumber(String flightNumber);

    @Query("SELECT f FROM Flight f WHERE " +
            "(:flightNumber IS NULL OR f.flightNumber LIKE %:flightNumber%) AND " +
            "(:departureAirportName IS NULL OR f.departureAirport.name LIKE %:departureAirportName%) AND " +
            "(:arrivalAirportName IS NULL OR f.arrivalAirport.name LIKE %:arrivalAirportName%)")
    List<Flight> searchFlights(@Param("flightNumber") String flightNumber,
                               @Param("departureAirportName") String departureAirportName,
                               @Param("arrivalAirportName") String arrivalAirportName);
}
