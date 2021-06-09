package com.example.ex4;

import com.example.ex4.Connected;
import com.example.ex4.beans.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.annotation.SessionScope;

@Configuration
public class Configurations {

    @Bean
//    @Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
    @SessionScope
    public User sessionBean(){
        User loggedIn = new User();
        System.out.println("a new session. value:" + loggedIn.getTest());
        return loggedIn;
    }

    @Bean
    @Scope("application")
    public Connected ConnectedUserBean(){
        return new Connected();
    }
}
