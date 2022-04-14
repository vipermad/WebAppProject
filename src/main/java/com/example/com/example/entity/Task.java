package com.example.com.example.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Task_list")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    @Column(name = "text")
    private String text;
    @Column(name = "date_create")
    private LocalDateTime date = LocalDateTime.now();


    public Task() {
    }

    public Task(String text) {
        this.text = text;
        date = LocalDateTime.now();
    }

    public Task(int id, String text) {
        this.id = id;
        this.text = text;
        date = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getDate() {
        return date;
    }
}