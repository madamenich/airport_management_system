package com.airline.airport_management_demo.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Passenger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "First name must not be blank")
    @Size(max = 50, message = "First name should not exceed 50 characters")
    @Column(nullable = false)
    private String firstName;

    @NotBlank(message = "Last name must not be blank")
    @Size(max = 50, message = "Last name should not exceed 50 characters")
    @Column(nullable = false)
    private String lastName;

    @NotBlank(message = "Email must not be blank")
    @Email(message = "Invalid email format")
    @Column(nullable = false, unique = true)
    private String email;

    @Pattern(regexp = "^\\+?[0-9]*$", message = "Invalid phone number format")
    @Size(min = 10, max = 15, message = "Phone number must be between 10 and 15 digits")
    @Column(unique = true)
    private String phoneNumber;

    @NotBlank(message = "Passport must not be blank")
    @Column(nullable = false, unique = true)
    private String passportNumber;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

