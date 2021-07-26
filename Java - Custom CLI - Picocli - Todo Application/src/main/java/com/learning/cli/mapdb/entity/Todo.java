package com.learning.cli.mapdb.entity;

import org.mapdb.Serializer;

import java.io.Serializable;
import java.util.Date;

public class Todo implements Serializable {
    Long id;
    String message;
    boolean done = false;
    Date createdOn = new Date();
    Date completedOn;

    public Todo() {
    }

    public Todo(String message) {
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getCompletedOn() {
        return completedOn;
    }

    public void setCompletedOn(Date completedOn) {
        this.completedOn = completedOn;
    }

    @Override
    public String toString() {
        return "Todo{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", done=" + done +
                ", createdOn=" + createdOn +
                ", completedOn=" + completedOn +
                '}';
    }
}
