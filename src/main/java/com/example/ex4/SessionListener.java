package com.example.ex4;

import com.example.ex4.beans.Connected;
import com.example.ex4.beans.User;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@Component

@WebListener
public class SessionListener implements HttpSessionListener {

    @Resource(name = "sessionBean")
    private User mySessionBean;

    @Resource(name = "ConnectedUserBean")
    private Connected connectedUsers;

//    public SessionListener() {
//        super();
//    }

    public void sessionCreated(final HttpSessionEvent event) {
        System.out.println("created session");
    }

    //does not fire when user deletes his cookies/sessions??
    public void sessionDestroyed(final HttpSessionEvent event) {
        System.out.println(mySessionBean.getName() + " session is destroyed. ");
//        System.out.println("bean: "+         mySessionBean.getName());
        connectedUsers.remove(mySessionBean.getName());
    }
}


