package com.example.chatroom.beans;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * this class is a bean that will be injected in the application scope.
 */
@Component
public class Connected implements Serializable {
    private LinkedList<String> connected;

    public Connected() {connected = new LinkedList<String>(); }
    public synchronized Integer connectedCount() {return connected.size();}

    //returns a list of all connected users, except the user that is asking for the list..
    public synchronized LinkedList<String> getConnected(String except) {
        LinkedList<String> copyOfConnected = new LinkedList<String>(connected);
        copyOfConnected.remove(except);
        return copyOfConnected;
    }
    public synchronized boolean exists(String name){
        return connected.contains(name);
    }
    public synchronized void add(String name){connected.add(name); }
    public synchronized void remove(String name){connected.remove(name); }
}
