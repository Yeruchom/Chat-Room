package com.example.ex4.beans;

import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class User implements Serializable {
    public User() {}
    private boolean loggedIn = false;
    private String myName;
    public boolean getLoggedIn(){return loggedIn;};
    public void setLoggedIn(boolean l){this.loggedIn = l;}
    public void setName(String name){this.myName = name;}
    public String getName() {
        return myName;
    }
}
