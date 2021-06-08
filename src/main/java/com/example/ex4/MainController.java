package com.example.ex4;
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


    @GetMapping("/")
    public String MainPage(Model model) {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam(name = "name") @NotBlank String name, Model model){

        mySessionBean.setTest(true);
        mySessionBean.setName(name);

        connectedUsers.add(name);

        model.addAttribute("loggedIn", "t");
        model.addAttribute("name", name);
        return "login";
    }

    @GetMapping("/logout")
    public ModelAndView Logout(){
        ModelAndView modelAndView = new ModelAndView("redirect:/");

        mySessionBean.setTest(false);
        connectedUsers.remove(mySessionBean.getName());
        return modelAndView;
    }


    @RequestMapping("/chatroom") //can get the name from session
    public String ChatRoom(Model model){
        if(!mySessionBean.getTest()) {
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


//
//    @RequestMapping("/chatroom/{name}") //can get the name from session
//    public String ChatRoom(@PathVariable(name = "name", required = false) String name, Model model){
//
//        if(!mySessionBean.getTest()) {
//            return "redirect:/";
//        }
//        List<String> copyOfConnected = new LinkedList<String>(connectedUsers.getConnected());
//
//        //just for test
//        copyOfConnected.add("A");
//        copyOfConnected.add("b");
//
//        copyOfConnected.remove(name);//remove the current user from the list of connected users
//
//        model.addAttribute("name", name);
//        model.addAttribute("connected", copyOfConnected);
//        return "chatroom";
//    }

    @PostMapping("/chatroom/message")
    public String SendMessage(@RequestParam String message, Model model){
        System.out.println("got message" +message );
        return "forward:/chatroom";
    }
//@RequestMapping("/chatroom/message")
//public String SendMessage(Model model){
//    System.out.println("got message");
//    return "forward:/chatroom";
//}
}