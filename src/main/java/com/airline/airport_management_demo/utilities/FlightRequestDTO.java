package com.airline.airport_management_demo.utilities;

public class FlightRequestDTO {

    private String flightNumber;
    private String departureTime; // Use ISO-8601 string format for simplicity
    private String arrivalTime;
    private Long departureAirportId;
    private Long arrivalAirportId;

    public FlightRequestDTO(String flightNumber, String departureTime, String arrivalTime, Long departureAirportId, Long arrivalAirportId) {
        this.flightNumber = flightNumber;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.departureAirportId = departureAirportId;
        this.arrivalAirportId = arrivalAirportId;
    }

    // Getters and Setters
    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Long getDepartureAirportId() {
        return departureAirportId;
    }

    public void setDepartureAirportId(Long departureAirportId) {
        this.departureAirportId = departureAirportId;
    }

    public Long getArrivalAirportId() {
        return arrivalAirportId;
    }

    public void setArrivalAirportId(Long arrivalAirportId) {
        this.arrivalAirportId = arrivalAirportId;
    }
}
