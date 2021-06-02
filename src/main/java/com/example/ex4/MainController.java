package com.example.ex4;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MainController {

//    @Resorce(name = "sessionBean")

    @GetMapping("/")
    public String Main(Model model, HttpServletRequest request){
        if(request.getSession()!= null && request.getSession().getAttribute("loggedIn") != null)
            model.addAttribute("loggedIn", true);

        return "login";
    };


    @GetMapping("/chatroom")
    public String ChatRoom(){
        return "chatroom";
    }
}
