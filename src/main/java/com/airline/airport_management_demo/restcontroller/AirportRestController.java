package com.airline.airport_management_demo.restcontroller;

import com.airline.airport_management_demo.entities.Airport;
import com.airline.airport_management_demo.services.AirportService;
import com.airline.airport_management_demo.utilities.APIResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/v1/private/airports")
public class AirportRestController {

    @Autowired
    private AirportService airportService;

    @GetMapping
    public ResponseEntity<APIResponse<List<Airport>>> getAllAirports() {
        List<Airport> airports = airportService.getAllAirports();
        return ResponseEntity.ok(new APIResponse<>(200, "Airports retrieved successfully", airports));
    }

    @Operation(summary = "Get airport by ID", description = "Retrieve an airport's details by its ID")
    @ApiResponse(responseCode = "200", description = "Airport retrieved successfully",
            content = @Content(schema = @Schema(implementation = APIResponse.class)))
    @ApiResponse(responseCode = "404", description = "Airport not found",
            content = @Content(schema = @Schema(implementation = APIResponse.class)))
    @ApiResponse(responseCode = "500", description = "Internal server error",
            content = @Content(schema = @Schema(implementation = APIResponse.class)))
    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<Airport>> getAirportById(@PathVariable Long id) {
        Optional<Airport> airportOptional = airportService.getAirportById(id);
        if (airportOptional.isPresent()) {
            return ResponseEntity.ok(new APIResponse<>(200, "Airport retrieved successfully", airportOptional.get()));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new APIResponse<>(404, "Airport not found with ID: " + id));
        }
    }

    @PostMapping
    public ResponseEntity<APIResponse<Airport>> createAirport(@RequestBody Airport airport) {
        Airport createdAirport = airportService.createAirport(airport);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new APIResponse<>(201, "Airport created successfully", createdAirport));
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<Airport>> updateAirport(@PathVariable Long id, @RequestBody Airport airport) {
        try {
            Airport updatedAirport = airportService.updateAirport(id, airport);
            return ResponseEntity.ok(new APIResponse<>(200, "Airport updated successfully", updatedAirport));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new APIResponse<>(404, "Airport not found with ID: " + id));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<Void>> deleteAirport(@PathVariable Long id) {
        try {
            airportService.deleteAirport(id);
            return ResponseEntity.ok(new APIResponse<>(200, "Airport deleted successfully"));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new APIResponse<>(404, "Airport not found with ID: " + id));
        }
    }
}
