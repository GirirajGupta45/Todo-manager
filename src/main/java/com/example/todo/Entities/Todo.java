package com.example.todo.Entities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

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
   @NotNull(message ="Target Date Time is required")
   @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm")
   @Column(name = "target_date_time", columnDefinition = "DATETIME")
    private LocalDateTime targetDateTime;

    private boolean done;

    public Todo(int ID, String description, String username, LocalDateTime targetDateTime, boolean done) {
        this.id = ID;
        this.description = description;
        this.username = username;
        this.targetDateTime = targetDateTime;
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

    public LocalDateTime getTargetDateTime() {
        return targetDateTime;
    }

    public void setTargetDateTime(LocalDateTime targetDateTime) {
        this.targetDateTime = targetDateTime;
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
                ", targetDateTime=" + targetDateTime +
                ", done=" + done +
                '}';
    }
    public String getFormattedTargetDateTime() {
        if (targetDateTime == null) return "";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return targetDateTime.format(formatter);
    }
}
