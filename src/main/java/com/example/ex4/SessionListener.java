package com.example.ex4;

import com.example.ex4.beans.Connected;
import com.example.ex4.beans.User;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

//a session listener that is fired when the session is destroyed and deletes
// that user from the list of connected users
@Component
@WebListener
public class SessionListener implements HttpSessionListener {

    @Resource(name = "sessionBean")
    private User mySessionBean;

    @Resource(name = "ConnectedUserBean")
    private Connected connectedUsers;

    public void sessionCreated(final HttpSessionEvent event) {
        System.out.println("created session");
    }

    //does not fire when user deletes his cookies/sessions in the browser??
    public void sessionDestroyed(final HttpSessionEvent event) {
//        System.out.println(mySessionBean.getName() + " session is destroyed. ");
        connectedUsers.remove(mySessionBean.getName());
    }
}


