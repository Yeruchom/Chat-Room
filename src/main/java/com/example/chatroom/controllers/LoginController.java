package com.example.chatroom.controllers;

import com.example.chatroom.MessageRepository;
import com.example.chatroom.beans.Connected;
import com.example.chatroom.beans.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

@Controller
public class LoginController {


    @Resource(name = "sessionBean")
    private User mySessionBean;

    @Resource(name = "ConnectedUserBean")
    private Connected connectedUsers; //all connected users. in application scope
//
//    @Autowired
//    private MessageRepository db;

    //returns the first login page
    @GetMapping("/")
    public String MainPage(Model model) {
        return "login";
    }


    //gets the users name from the login page
    //returns the login view again with an error message (via thymeleaf) if the name is empty or it exists
    //and a welcome message with a link to the chatroom otherwise
    @PostMapping("/login")
    public String login(@RequestParam String name, Model model) {
        if(name.trim() == "") {
            model.addAttribute("empty", "t");
            return "login";
        }

        if(connectedUsers.exists(name)){
            model.addAttribute("exists", "t");
            model.addAttribute("name", name);
            return "login";
        }

        mySessionBean.setLoggedIn(true);
        mySessionBean.setName(name);
        connectedUsers.add(name);//add him to the list of connected users

        model.addAttribute("loggedIn", "t");
        model.addAttribute("name", name);
        return "login";
    }
}
