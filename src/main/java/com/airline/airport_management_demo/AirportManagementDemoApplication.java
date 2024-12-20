package com.airline.airport_management_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class AirportManagementDemoApplication {

    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode("password123");
        System.out.println(encodedPassword);
        SpringApplication.run(AirportManagementDemoApplication.class, args);
    }

}
