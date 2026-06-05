package com.Hr.Market.dto;

import com.Hr.Market.Entity.Roles;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;


public class DtoRegister {

    @NotBlank
    @Size(min = 3, max = 20)
    @Pattern(
            regexp = "^[a-zA-Z]+$",
            message = "Username must contain letters only"
    )
    private String username;

    @Email(message = "Invalid email")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;

    @NotBlank(message = "Role is required")
    private String role;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public DtoRegister(String email, String password, String role, String username) {
        this.email = email;
        this.password = password;
        this.role = role;
        this.username = username;
    }
}
