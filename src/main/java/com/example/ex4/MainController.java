package com.example.ex4;

import com.example.ex4.Connected;
import com.example.ex4.beans.User;
import com.example.ex4.Configurations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;
import java.util.LinkedList;
import java.util.List;

@Controller
public class MainController {

    @Resource(name = "sessionBean")
    private User mySessionBean;

    @Resource(name = "ConnectedUserBean")
    private Connected connectedUsers;

    @Autowired
    private MessageRepository db;

    @GetMapping("/")
    public String MainPage(Model model) {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam(name = "name") @NotBlank String name, Model model) {

        mySessionBean.setTest(true);
        mySessionBean.setName(name);

        connectedUsers.add(name);

        model.addAttribute("loggedIn", "t");
        model.addAttribute("name", name);
        return "login";
    }

    @GetMapping("/logout")
    public ModelAndView Logout() {
        ModelAndView modelAndView = new ModelAndView("redirect:/");

        mySessionBean.setTest(false);
        connectedUsers.remove(mySessionBean.getName());
        return modelAndView;
    }


    @RequestMapping("/chatroom")
    public String ChatRoom(Model model) {
        if (!mySessionBean.getTest()) {
            System.out.println("not logged in. redirecting to login page");
            return "redirect:/";
        }

        List<String> copyOfConnected = new LinkedList<String>(connectedUsers.getConnected());
        String name = mySessionBean.getName();

        //just for test
        copyOfConnected.add("A");
        copyOfConnected.add("b");

        copyOfConnected.remove(name);//remove the current user from the list of connected users

        model.addAttribute("name", name);
        model.addAttribute("connected", copyOfConnected);
        return "chatroom";
    }


    @PostMapping("/chatroom/sendMessage")
    public String SendMessage(@RequestParam String message, Model model) {
        System.out.println("got message " + message);
        //validate the message!!!!!!!!!!!!!!!!!
        db.save(new Message(mySessionBean.getName(), message));

        return "forward:/chatroom";
    }

    @GetMapping("/chatroom/getChat")
    public @ResponseBody List<Message> GetMessage(){
        return db.findAll();
    }


//@RequestMapping("/chatroom/message")
//public String SendMessage(Model model){
//    System.out.println("got message");
//    return "forward:/chatroom";
//}
}
