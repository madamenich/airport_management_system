package com.airline.airport_management_demo.services;

import com.airline.airport_management_demo.entities.User;
import com.airline.airport_management_demo.reporitories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Create a new user.
     *
     * @param user User object to be created.
     * @return The created user.
     */
    public User createUser(User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists: " + user.getUsername());
        }
        return userRepository.save(user);
    }

    /**
     * Get a user by ID.
     *
     * @param userId The ID of the user.
     * @return The user with the given ID.
     */
    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));
    }

    /**
     * Get a user by username.
     *
     * @param username The username of the user.
     * @return The user with the given username.
     */
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found with username: " + username));
    }

    /**
     * Get all users.
     *
     * @return A list of all users.
     */
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Add roles to a user.
     *
     * @param userId The ID of the user.
     * @param roles  The set of roles to add.
     * @return The updated user.
     */
    public User addRolesToUser(Long userId, Set<String> roles) {
        User user = getUserById(userId);
        user.getRoles().addAll(roles);
        return userRepository.save(user);
    }

    /**
     * Update a user's roles.
     *
     * @param userId The ID of the user.
     * @param roles  The new set of roles.
     * @return The updated user.
     */
    public User updateRolesForUser(Long userId, Set<String> roles) {
        User user = getUserById(userId);
        user.setRoles((List<String>) roles);
        return userRepository.save(user);
    }

    /**
     * Delete a user by ID.
     *
     * @param userId The ID of the user to delete.
     */
    public void deleteUser(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new RuntimeException("User not found with ID: " + userId);
        }
        userRepository.deleteById(userId);
    }

    /**
     * Check if a user exists by username.
     *
     * @param username The username to check.
     * @return true if the user exists, false otherwise.
     */
    public boolean userExistsByUsername(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    /**
     * Check if a user exists by ID.
     *
     * @param userId The ID to check.
     * @return true if the user exists, false otherwise.
     */
    public boolean userExistsById(Long userId) {
        return userRepository.existsById(userId);


    }
}

