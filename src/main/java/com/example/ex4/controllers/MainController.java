package com.example.ex4.controllers;

import com.example.ex4.Message;
import com.example.ex4.MessageRepository;
import com.example.ex4.beans.Connected;
import com.example.ex4.beans.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.LinkedList;
import java.util.List;

//@Validated
@Controller
public class MainController {

    @Resource(name = "sessionBean")
    private User mySessionBean;

    @Resource(name = "ConnectedUserBean")
    private Connected connectedUsers;

    @Autowired
    private MessageRepository db;

//    @Resource(name = "SessionListenerFoo")
//    private mySessionListener sl;

    @GetMapping("/")
    public String MainPage(Model model) {
        return "login";
    }


    @PostMapping("/login")
    //    public String login(@RequestParam(name = "name") @NotBlank String name, Model model) {
    public String login(@RequestParam String name, Model model) {
        if(name.trim() == "") {
            model.addAttribute("empty", "t");
            return "login";

//            result.rejectValue("name", "errCode", "name can't be empty");
//            return "login";
        }

        if(connectedUsers.exists(name)){
            model.addAttribute("exists", "t");
            model.addAttribute("name", name);

            return "login";
        }

        mySessionBean.setLoggedIn(true);
        mySessionBean.setName(name);

        connectedUsers.add(name);

        model.addAttribute("loggedIn", "t");
        model.addAttribute("name", name);
        return "login";
    }

    @GetMapping("/logout")
    public ModelAndView Logout(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("redirect:/");

//        mySessionBean.setTest(false);
//        connectedUsers.remove(mySessionBean.getName()); will happen in the session listener
        request.getSession().invalidate();
        return modelAndView;
    }


    @GetMapping("/connectedUsers")
    public @ResponseBody List<String> ConnectedUsers(Model model) {

        List<String> copyOfConnected = new LinkedList<String>(connectedUsers.getConnected(mySessionBean.getName()));

        copyOfConnected.add("A");
        copyOfConnected.add("b");

        return copyOfConnected;
    }


    @RequestMapping("/chatroom")
    public String ChatRoom(Message message, Model model) {

        model.addAttribute("name", mySessionBean.getName());
        return "chatroom";
    }


    @PostMapping("/chatroom/sendMessage")
    public String SendMessage(@Valid Message message, BindingResult result, Model model) {
        if(result.hasErrors()){
            model.addAttribute("name", mySessionBean.getName());
            return "chatroom"; //forward:/chatroom
        }

        db.save(message);
            return "forward:/chatroom";
    }

    @GetMapping("/chatroom/getChat")
    public @ResponseBody List<Message> GetMessage(){
        return db.findAll();
    }

    @GetMapping("/chatroom/search")
    public String Search(Model model){

        return "search";
    }

//@RequestMapping("/chatroom/message")
//public String SendMessage(Model model){
//    System.out.println("got message");
//    return "forward:/chatroom";
//}
}
