package com.example.todo.Beans;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.lang.NonNull;

@Entity
public class Todo {
    @Id
    @GeneratedValue
    private int id;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
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
