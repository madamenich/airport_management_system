package com.airline.airport_management_demo.configurations;

import com.airline.airport_management_demo.securities.JwtFilter;
import com.airline.airport_management_demo.securities.JwtUtil;
import com.airline.airport_management_demo.services.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final JwtFilter jwtFilter;
    private final CustomUserDetailsService userDetailsService;


    public SecurityConfig(JwtFilter jwtFilter, CustomUserDetailsService userDetailsService) {
        this.jwtFilter = jwtFilter;
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests()
                // Public routes
                .requestMatchers("/", "/api/auth/**", "/login", "/register", "/flights/**", "/css/**", "/js/**", "/images/**", "/swagger-ui/**", "/v3/api-docs/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/airports").permitAll()
                // Restrict admin routes
                .requestMatchers(HttpMethod.PUT, "/airports/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/airports/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST, "/airports/**").hasRole("ADMIN")
                // Authenticated routes for API
                .requestMatchers("/api/**").authenticated()
                // Secure all other routes
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .permitAll()
                .and()
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class); // Add the JwtFilter here

        return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
}

