package com.example.ex4;

import com.example.ex4.beans.User;
import filters.LoggingInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;


//this is a class for configuring our interceptor
@EnableWebMvc
@Configuration
public class MyConfig implements WebMvcConfigurer {

    @Resource(name = "sessionBean")
    private User mySessionBean;

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(new LoggingInterceptor(mySessionBean)).addPathPatterns("/**");
    }


}