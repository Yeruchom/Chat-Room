package com.example.ex4;
import net.bytebuddy.dynamic.scaffold.MethodGraph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Controller
public class MainController {

    @Resource(name = "sessionBean")
    private Test mySessionBean;

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

    @GetMapping("/chatroom/{name}") //can get the name from session
    public String ChatRoom(@PathVariable String name, Model model){
//        mySessionBean.setTest(false);
        if(!mySessionBean.getTest())
        {
//            return "redirect:/";
            return "login"; //fix and check
        }
        List<String> copyOfConnected = new LinkedList<String>(connectedUsers.getConnected());

        //just for test
        copyOfConnected.add("A");
        copyOfConnected.add("b");

        copyOfConnected.remove(name);//need to remove myself from connected users

        model.addAttribute("name", name);
        model.addAttribute("connected", copyOfConnected);
        return "chatroom";
    }

    @GetMapping("/logout")
    public ModelAndView Logout(){
        ModelAndView modelAndView = new ModelAndView("redirect:/");

        mySessionBean.setTest(false);
        connectedUsers.remove(mySessionBean.getName());
        return modelAndView;
    }
}