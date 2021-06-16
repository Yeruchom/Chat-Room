package com.example.ex4.beans;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

@Component
public class Connected implements Serializable {
    private LinkedList<String> connected;

    public Connected() {connected = new LinkedList<String>();}

//    public synchronized LinkedList<String> getConnected() {return connected;}

    public synchronized LinkedList<String> getConnected(String except) {
        LinkedList<String> copyOfConnected = new LinkedList<String>(connected);
        copyOfConnected.remove(except);
        return copyOfConnected;
    }
    public synchronized boolean exists(String name){
        return connected.contains(name);
    }
    public synchronized void add(String name){connected.add(name);}
    public synchronized void remove(String name){connected.remove(name);}
}
