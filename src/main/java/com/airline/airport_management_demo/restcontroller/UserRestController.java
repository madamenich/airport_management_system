package com.airline.airport_management_demo.restcontroller;


import com.airline.airport_management_demo.entities.User;
import com.airline.airport_management_demo.services.UserService;
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
import java.util.Set;

@RestController
@RequestMapping("/api/v1/private/users")
public class UserRestController {

    @Autowired
    private UserService userService;

    @Operation(summary = "Get all users", description = "Retrieve a list of all users")
    @ApiResponse(responseCode = "200", description = "Users retrieved successfully",
            content = @Content(schema = @Schema(implementation = APIResponse.class)))
    @GetMapping
    public ResponseEntity<APIResponse<List<User>>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(new APIResponse<>(200, "Users retrieved successfully", users));
    }

    @Operation(summary = "Create a new user", description = "Add a new user to the system")
    @ApiResponse(responseCode = "201", description = "User created successfully",
            content = @Content(schema = @Schema(implementation = APIResponse.class)))
    @PostMapping
    public ResponseEntity<APIResponse<User>> createUser(@RequestBody User user) {
        User createdUser = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new APIResponse<>(201, "User created successfully", createdUser));
    }

    @Operation(summary = "Get user by ID", description = "Retrieve a user's details by their ID")
    @ApiResponse(responseCode = "200", description = "User retrieved successfully",
            content = @Content(schema = @Schema(implementation = APIResponse.class)))
    @ApiResponse(responseCode = "404", description = "User not found",
            content = @Content(schema = @Schema(implementation = APIResponse.class)))
    @GetMapping("/{userId}")
    public ResponseEntity<APIResponse<User>> getUserById(@PathVariable Long userId) {
        Optional<User> userOptional = Optional.ofNullable(userService.getUserById(userId));
        if (userOptional.isPresent()) {
            return ResponseEntity.ok(new APIResponse<>(200, "User retrieved successfully", userOptional.get()));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new APIResponse<>(404, "User not found with ID: " + userId));
        }
    }

    @Operation(summary = "Add roles to a user", description = "Add new roles to an existing user")
    @ApiResponse(responseCode = "200", description = "Roles added successfully",
            content = @Content(schema = @Schema(implementation = APIResponse.class)))
    @PostMapping("/{userId}/roles")
    public ResponseEntity<APIResponse<User>> addRolesToUser(@PathVariable Long userId, @RequestBody Set<String> roles) {
        User updatedUser = userService.addRolesToUser(userId, roles);
        return ResponseEntity.ok(new APIResponse<>(200, "Roles added successfully", updatedUser));
    }

    @Operation(summary = "Update roles for a user", description = "Replace the roles of an existing user")
    @ApiResponse(responseCode = "200", description = "Roles updated successfully",
            content = @Content(schema = @Schema(implementation = APIResponse.class)))
    @PutMapping("/{userId}/roles")
    public ResponseEntity<APIResponse<User>> updateRolesForUser(@PathVariable Long userId, @RequestBody Set<String> roles) {
        User updatedUser = userService.updateRolesForUser(userId, roles);
        return ResponseEntity.ok(new APIResponse<>(200, "Roles updated successfully", updatedUser));
    }

    @Operation(summary = "Delete a user", description = "Remove a user from the system")
    @ApiResponse(responseCode = "200", description = "User deleted successfully",
            content = @Content(schema = @Schema(implementation = APIResponse.class)))
    @ApiResponse(responseCode = "404", description = "User not found",
            content = @Content(schema = @Schema(implementation = APIResponse.class)))
    @DeleteMapping("/{userId}")
    public ResponseEntity<APIResponse<Void>> deleteUser(@PathVariable Long userId) {
        try {
            userService.deleteUser(userId);
            return ResponseEntity.ok(new APIResponse<>(200, "User deleted successfully"));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new APIResponse<>(404, "User not found with ID: " + userId));
        }
    }
}



