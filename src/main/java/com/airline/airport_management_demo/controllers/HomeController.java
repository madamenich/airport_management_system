package com.airline.airport_management_demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class HomeController {
    /**
     * Route for the homepage.
     * @return the name of the Thymeleaf template for the homepage (index.html)
     */
    @GetMapping("/")
    public String home() {
        return "index"; // Assuming the homepage Thymeleaf template is named 'index.html'
    }

    /**
     * Route for the login page.
     * @return the name of the Thymeleaf template for the login page (login.html)
     */
//    @GetMapping("/login")
//    public String login() {
//        return "login"; // Assuming the login Thymeleaf template is named 'login.html'
//    }

    /**
     * Route for the registration page.
     * @return the name of the Thymeleaf template for the registration page (register.html)
     */
//    @GetMapping("/register")
//    public String register() {
//        return "register"; // Assuming the registration Thymeleaf template is named 'register.html'
//    }

}
