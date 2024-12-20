package com.airline.airport_management_demo.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Flight number must not be blank")
    @Size(max = 20, message = "Flight number should not exceed 20 characters")
    @Column(nullable = false, unique = true)
    private String flightNumber;

    @NotNull(message = "Departure time must not be null")
    @Column(nullable = false)
    private LocalDateTime departureTime;

    @NotNull(message = "Arrival time must not be null")
    @Column(nullable = false)
    private LocalDateTime arrivalTime;

    @ManyToOne
    @JoinColumn(name = "departure_airport_id", nullable = false)
    @JsonBackReference
    private Airport departureAirport;

    @ManyToOne
    @JoinColumn(name = "arrival_airport_id", nullable = false)
    @JsonManagedReference
    private Airport arrivalAirport;

    @OneToMany(mappedBy = "flight", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Ticket> tickets;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Airport getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(Airport departureAirport) {
        this.departureAirport = departureAirport;
    }

    public Airport getArrivalAirport() {
        return arrivalAirport;
    }

    public void setArrivalAirport(Airport arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }
}
