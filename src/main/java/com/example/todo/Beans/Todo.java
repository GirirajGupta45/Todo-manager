package com.example.todo.Beans;

import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Todo {
    @Id
    @GeneratedValue
    private int id;
    private String Description;
    private String Username;
    private LocalDate targetDate;
    private boolean done;

    public Todo(int ID, String description, String username, LocalDate targetDate, boolean done) {
        this.id = ID;
        Description = description;
        Username = username;
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
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
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
                ", Description='" + Description + '\'' +
                ", Username='" + Username + '\'' +
                ", targetDate=" + targetDate +
                ", done=" + done +
                '}';
    }
}
