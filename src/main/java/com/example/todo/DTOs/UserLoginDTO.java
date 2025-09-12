package com.example.todo.DTOs;

import jakarta.validation.constraints.NotBlank;
import org.springframework.stereotype.Component;

@Component
public class UserLoginDTO {
    @NotBlank(message = "Username is required")
    private String username;
    @NotBlank(message = "Password is required")
    private String password;

    public UserLoginDTO() {
    }

    public UserLoginDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public @NotBlank(message = "Username is required") String getUsername() {
        return username;
    }

    public void setUsername(@NotBlank(message = "Username is required") String username) {
        this.username = username;
    }

    public @NotBlank(message = "Password is required") String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank(message = "Password is required") String password) {
        this.password = password;
    }
}
