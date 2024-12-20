package com.airline.airport_management_demo.reporitories;

import com.airline.airport_management_demo.entities.Airport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirportRepository extends JpaRepository<Airport,Long> {

    // Custom query to find an airport by its code
    Airport findByCode(String code);

    // Custom query to find an airport by its name
    Airport findByName(String name);

    boolean existsByName(String name);

}
