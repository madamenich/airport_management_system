package com.airline.airport_management_demo.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Controller
public class AuthenticationController {
    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/login")
    public String showLoginPage(Model model) {
        model.addAttribute("error", false);
        return "login";
    }

    @PostMapping("/login")
    public String handleLogin(String username, String password, Model model) {
        // Prepare API URL
        String apiUrl = "http://localhost:8080/api/users";

        try {
            // Call the REST API to validate user
            Map<String, String> request = new HashMap<>();
            request.put("username", username);
            request.put("password", password);

            Boolean isValid = restTemplate.postForObject(apiUrl + "/validate", request, Boolean.class);

            if (Boolean.TRUE.equals(isValid)) {
                // Redirect to dashboard if successful
                return "redirect:/dashboard";
            } else {
                // Invalid login
                model.addAttribute("error", true);
                return "login";
            }
        } catch (Exception e) {
            // Handle API errors
            model.addAttribute("error", true);
            return "login";
        }
    }
}
