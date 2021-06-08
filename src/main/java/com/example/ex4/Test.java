package com.example.ex4;

import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class Test implements Serializable {
    public Test() {}
    private boolean test = false;
    private String myName;
    public boolean getTest(){return test;};
    public void setTest(boolean t){this.test = t;}
    public void setName(String name){this.myName = name;}
    public String getName() {
        return myName;
    }
}
