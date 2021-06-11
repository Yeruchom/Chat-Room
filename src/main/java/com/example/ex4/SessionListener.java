package com.example.ex4;

import com.example.ex4.beans.User;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.*;
import org.springframework.session.events.SessionCreatedEvent;
import org.springframework.session.events.SessionDestroyedEvent;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.concurrent.atomic.AtomicInteger;


//here im trying the first way of creating a listener. does not work
//@Component
//public class SessionListener {
//
//    @EventListener
//    public void SessionDestroydListener(SessionDestroyedEvent event) {
//        System.out.print("session destroyed");
//    }
//
//    @EventListener
//    public void SessionCreatedListener(SessionCreatedEvent event) {
//        System.out.print("session created");
//    }
//}




@Component
//public class SessionListener implements ApplicationListener<SessionCreatedEvent> {
//
//    @Override
//    public void onApplicationEvent(final SessionCreatedEvent cse) {
//        System.out.println("****session created. ");
//    }
//}
@WebListener
public class SessionListener implements HttpSessionListener {

    private final AtomicInteger activeSessions;

//    @Resource(name = "sessionBean")
//    private User mySessionBean;

    public SessionListener() {
        super();

        activeSessions = new AtomicInteger();
    }

    public int getTotalActiveSession() {
        return activeSessions.get();
    }
//
    public void sessionCreated(final HttpSessionEvent event) {
        System.out.println("created session");
//        System.out.println("create session for " + mySessionBean.getName());
    activeSessions.incrementAndGet();
    }

    //does not fire when user deletes his cookies/sessions??
    public void sessionDestroyed(final HttpSessionEvent event) {
        System.out.println("some session is destroyed. witch one??????");

        activeSessions.decrementAndGet();
    }
}



//
//    @EventListener
//    public void handleContextRefreshed (ContextRefreshedEvent event) {
//        System.out.print(">>>>>>> ContextListenerAllEvents: context refreshed event fired: ");
//        System.out.println(event);
//    }
//
//    @EventListener
//    public void handleContextStarted (ContextStartedEvent event) {
//        System.out.print(">>>>>>> ContextListenerAllEvents: context started event fired: ");
//        System.out.println(event);
//    }
//
//    @EventListener
//    public void handleContextStopped (ContextStoppedEvent event) {
//        System.out.print(">>>>>>> ContextListenerAllEvents: context stopped event fired: ");
//        System.out.println(event);
//    }
//
//    @EventListener
//    public void handleContextClosed (ContextClosedEvent event) {
//        System.out.print(">>>>>>> ContextListenerAllEvents: context  closed event fired: ");
//        System.out.println(event);
//    }
//}
