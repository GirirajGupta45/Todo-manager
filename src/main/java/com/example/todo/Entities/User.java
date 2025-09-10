package com.example.todo.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class User {
    @Id
    @GeneratedValue
    private Long id;
    @NotNull(message = "Username is required")
//    @Size(min=3, message = "Too small Username: min length 3 characters")
    @Column(nullable = false, unique = true)
    private String username;
    @NotNull(message = "Password is required")
//    @Size(min = 6, max = 20, message = "Password must be between 6 and 20 characters")
    private String password;
    @NotNull
    private String email;



    public User() {
    }

    public User( String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public @NotNull(message = "Username is required")  String getUsername() {
        return username;
    }

    public void setUsername(@NotNull(message = "Username is required")  String username) {
        this.username = username;
    }

    public @NotNull(message = "Password is required")   String getPassword() {
        return password;
    }

    public void setPassword(@NotNull(message = "Password is required")  String password) {
        this.password = password;
    }

    public @NotNull String getEmail() {
        return email;
    }

    public void setEmail(@NotNull String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
