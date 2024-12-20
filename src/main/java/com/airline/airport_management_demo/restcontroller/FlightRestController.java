package com.airline.airport_management_demo.restcontroller;

import com.airline.airport_management_demo.entities.Flight;
import com.airline.airport_management_demo.services.FlightService;
import com.airline.airport_management_demo.utilities.APIResponse;

import com.airline.airport_management_demo.utilities.FlightRequestDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/private/flights")
public  class  FlightRestController{

    @Autowired
    private FlightService flightService;

    /**
     * Get all flights
     */
    @Operation(summary = "Get all flights", description = "Retrieve a list of all flights")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Flights retrieved successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Flight.class)))
    })
    @GetMapping
    public ResponseEntity<APIResponse<List<Flight>>> getAllFlights() {
        List<Flight> flights = flightService.getAllFlights();
        return ResponseEntity.ok(new APIResponse<>(200, "Flights retrieved successfully", flights));
    }

    /**
     * Get a flight by ID
     */
    @Operation(summary = "Get a flight by ID", description = "Retrieve details of a specific flight by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Flight retrieved successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Flight.class))),
            @ApiResponse(responseCode = "404", description = "Flight not found",
                    content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<Flight>> getFlightById(@PathVariable Long id) {
        Optional<Flight> flightOptional = flightService.getFlightById(id);
        return flightOptional.map(flight ->
                        ResponseEntity.ok(new APIResponse<>(200, "Flight retrieved successfully", flight)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new APIResponse<>(404, "Flight not found with ID: " + id, null)));
    }

    /**
     * Create a new flight
     */
    @Operation(summary = "Create a new flight", description = "Add a new flight to the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Flight created successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Flight.class)))
    })
    @PostMapping
    public ResponseEntity<APIResponse<Flight>> createFlight(@RequestBody FlightRequestDTO flightRequest) {
        try {
            Flight createdFlight = flightService.createFlight(flightRequest);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new APIResponse<>(201, "Flight created successfully", createdFlight));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new APIResponse<>(400, "Error creating flight: " + e.getMessage(), null));
        }
    }

    /**
     * Update an existing flight
     */
    @Operation(summary = "Update an existing flight", description = "Update the details of an existing flight by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Flight updated successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Flight.class))),
            @ApiResponse(responseCode = "404", description = "Flight not found",
                    content = @Content(mediaType = "application/json"))
    })
    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<Flight>> updateFlight(@PathVariable Long id, @RequestBody Flight flight) {
        try {
            Flight updatedFlight = flightService.updateFlight(id, flight);
            return ResponseEntity.ok(new APIResponse<>(200, "Flight updated successfully", updatedFlight));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new APIResponse<>(404, "Flight not found with ID: " + id, null));
        }
    }

    /**
     * Delete a flight by ID
     */
    @Operation(summary = "Delete a flight by ID", description = "Remove a flight from the system by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Flight deleted successfully",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Flight not found",
                    content = @Content(mediaType = "application/json"))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<Void>> deleteFlight(@PathVariable Long id) {
        try {
            flightService.deleteFlight(id);
            return ResponseEntity.ok(new APIResponse<>(200, "Flight deleted successfully", null));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new APIResponse<>(404, "Flight not found with ID: " + id, null));
        }
    }
}
