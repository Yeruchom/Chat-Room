package com.example.chatroom;

import com.example.chatroom.beans.User;
import com.example.chatroom.beans.Connected;
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

