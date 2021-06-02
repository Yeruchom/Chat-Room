package com.example.ex4;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.WebApplicationContext;

@Configuration
public class sessionBean {
    private boolean loggedIn = false;

    @Bean
    @Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public void CreateSession() {

    }

    public boolean isLoggedIn() {return loggedIn;}
    public void setLoggedIn(boolean logged){loggedIn=logged;}
}
