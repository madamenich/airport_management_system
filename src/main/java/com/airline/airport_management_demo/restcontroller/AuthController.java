package com.airline.airport_management_demo.restcontroller;

import com.airline.airport_management_demo.entities.User;
import com.airline.airport_management_demo.securities.JwtUtil;
import com.airline.airport_management_demo.services.CustomUserDetailsService;
import com.airline.airport_management_demo.utilities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil, CustomUserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }



    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

            // Generate JWT token
            String token = jwtUtil.generateToken(authRequest.getUsername());

            return ResponseEntity.ok(new AuthResponse(token));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorResponse("Invalid credentials"));
        }
    }

//
@PostMapping("/register")
public ResponseEntity<?> registerUser(@RequestBody RegisterRequest registerRequest) {
    if (userDetailsService.usernameExists(registerRequest.getUsername())) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse("Username is already taken"));
    }

    // Create and save the new user
    User newUser = new User();
    newUser.setUsername(registerRequest.getUsername());
    newUser.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
    newUser.setRoles(List.of("ROLE_USER")); // Automatically assign "USER" role
    newUser.setEnabled(true);

    userDetailsService.saveUser(newUser);

    return ResponseEntity.ok(new APIResponse<>(201, "User registered successfully", null));
}


}
