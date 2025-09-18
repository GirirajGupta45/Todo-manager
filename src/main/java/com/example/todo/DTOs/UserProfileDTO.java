package com.example.todo.DTOs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserProfileDTO {
    @NotBlank(message = "Username is required")
    @Size(min = 4, max = 30)
    private String username;
    @NotBlank(message = "Email is required")
    @Email(message = "Enter a valid email")
    private String email;

    public UserProfileDTO(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserProfileDTO() {
    }
}
