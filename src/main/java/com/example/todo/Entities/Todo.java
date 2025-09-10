package com.example.todo.Entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Todo {
    @Id
    @GeneratedValue
    private int id;
   @NotNull(message = "description is required")
   @Size(min=3, message = "Too small description: min length 3 characters")
    private String description;
    @Column(nullable = false)
    private String username;
   @NotNull(message ="Target Date is required")
    private LocalDate targetDate;
    private boolean done;

    public Todo(int ID, String description, String username, LocalDate targetDate, boolean done) {
        this.id = ID;
        this.description = description;
        this.username = username;
        this.targetDate = targetDate;
        this.done = done;
    }

    public Todo() {
    }



    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDate getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(LocalDate targetDate) {
        this.targetDate = targetDate;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    @Override
    public String toString() {
        return "Todo{" +
                "ID=" + id +
                ", Description='" + description + '\'' +
                ", Username='" + username + '\'' +
                ", targetDate=" + targetDate +
                ", done=" + done +
                '}';
    }
}
