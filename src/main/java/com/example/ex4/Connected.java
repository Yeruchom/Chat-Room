package com.example.ex4;

import org.springframework.stereotype.Component;

import java.util.LinkedList;

@Component
public class Connected{
    private LinkedList<String> connected;

    public Connected() {connected = new LinkedList<String>();}

    public LinkedList<String> getConnected() {return connected;}
    public void add(String name){connected.add(name);}
    public void remove(String name){connected.remove(name);}
}
