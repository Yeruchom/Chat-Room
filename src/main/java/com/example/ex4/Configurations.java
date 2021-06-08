package com.example.ex4;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.annotation.SessionScope;

@Configuration
public class Configurations {

    @Bean
//    @Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
    @SessionScope
    public Test sessionBean(){
        Test loggedIn = new Test();
        return loggedIn;
    }

    @Bean
    @Scope("application")
    public Connected ConnectedUserBean(){
        Connected connectedUsers = new Connected();
        return connectedUsers;
    }
}
