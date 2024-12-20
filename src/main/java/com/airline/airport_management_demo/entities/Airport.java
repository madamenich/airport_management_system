package com.airline.airport_management_demo.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Airport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Airport name must not be blank")
    @Size(max = 100, message = "Airport name should not exceed 100 characters")
    @Column(nullable = false, unique = true)
    private String name;

    @NotBlank(message = "Airport code must not be blank")
    @Size(max = 10, message = "Airport code should not exceed 10 characters")
    @Column(nullable = false, unique = true)
    private String code;

    @NotBlank(message = "Location must not be blank")
    @Column(nullable = false)
    private String location;

    @NotBlank(message = "Country must not be blank")
    @Column(nullable = false)
    private String country;

    @OneToMany(mappedBy = "departureAirport", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Flight> departureFlights;

    @OneToMany(mappedBy = "arrivalAirport", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Flight> arrivalFlights;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<Flight> getDepartureFlights() {
        return departureFlights;
    }

    public void setDepartureFlights(List<Flight> departureFlights) {
        this.departureFlights = departureFlights;
    }

    public List<Flight> getArrivalFlights() {
        return arrivalFlights;
    }

    public void setArrivalFlights(List<Flight> arrivalFlights) {
        this.arrivalFlights = arrivalFlights;
    }
}
