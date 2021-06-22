package com.example.ex4;

import com.example.ex4.beans.Connected;
import com.example.ex4.beans.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.annotation.SessionScope;

//a configuration class that injects all beans according to there scope
@Configuration
public class Configurations {

    @Bean
    @SessionScope
    public User sessionBean(){ return  new User(); }

    @Bean
    @Scope("application")
    public Connected ConnectedUserBean(){
        return new Connected();
    }

}

