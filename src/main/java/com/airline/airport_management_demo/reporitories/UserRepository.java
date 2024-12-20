package com.airline.airport_management_demo.reporitories;

import com.airline.airport_management_demo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // Find a user by their username
    Optional<User> findByUsername(String username);

    // Check if a user exists by username
    boolean existsByUsername(String username);
}