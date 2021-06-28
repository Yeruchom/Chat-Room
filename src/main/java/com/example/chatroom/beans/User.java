package com.example.chatroom.beans;

import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class User implements Serializable {
    public User() {}
    private boolean loggedIn = false;
    private String myName;
    public synchronized boolean getLoggedIn(){return loggedIn;};
    public synchronized void setLoggedIn(boolean l){this.loggedIn = l;}
    public void setName(String name){this.myName = name;}
    public synchronized String getName() {
        return myName;
    }
}
