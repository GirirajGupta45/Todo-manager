package com.example.todo.Beans;

import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Todo {
    @Id
    @GeneratedValue
    private int ID;
    private String Description;
    private String Username;
    private LocalDate targetDate;
    private boolean done;

    public Todo(int ID, String description, String username, LocalDate targetDate, boolean done) {
        this.ID = ID;
        Description = description;
        Username = username;
        this.targetDate = targetDate;
        this.done = done;
    }

    public Todo() {
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
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
                "ID=" + ID +
                ", Description='" + Description + '\'' +
                ", Username='" + Username + '\'' +
                ", targetDate=" + targetDate +
                ", done=" + done +
                '}';
    }
}
