package com.airline.airport_management_demo.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Ticket number must not be blank")
    @Size(max = 20, message = "Ticket number should not exceed 20 characters")
    @Column(nullable = false, unique = true)
    private String ticketNumber;

    @ManyToOne
    @JoinColumn(name = "flight_id", nullable = false)
    private Flight flight;

    @ManyToOne
    @JoinColumn(name = "passenger_id", nullable = false)
    private Passenger passenger;

    @ManyToOne
    @JoinColumn(name = "users.id", nullable = true)
    private User user;

    @NotBlank(message = "Seat number must not be blank")
    @Column(nullable = false)
    private String seatNumber;

    @NotNull(message = "Price must not be null")
    @Positive(message = "Price must be greater than zero")
    @Column(nullable = false)
    private Double price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(String ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
