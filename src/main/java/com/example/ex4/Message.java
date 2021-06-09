package com.example.ex4;

import net.bytebuddy.implementation.bind.annotation.Default;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Message{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotEmpty(message = "Name is mandatory")
    private String name;

    @NotEmpty(message = "Can't enter an empty message")
    private String message;

//    @GeneratedValue(LocalTime.now()) //default value
    private LocalTime time;

    public Message() {}

    public Message(String n, String m) {//, LocalTime t = LocalDate.now()
        this.name = n;
        this.message = m;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setName(String n) {
        this.name = n;
    }

    public void setMessage(String m) {
        this.message = m;
    }

    public String getName() {
        return name;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "Message{" + "id=" + id + ", name=" + name + ", message=" + message +'}';
    }
}

