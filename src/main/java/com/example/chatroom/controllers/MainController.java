package com.example.chatroom.controllers;

import com.example.chatroom.beans.User;
import com.example.chatroom.Message;
import com.example.chatroom.MessageRepository;
import com.example.chatroom.beans.Connected;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


/**
 *
 */
@Controller
public class MainController {

    @Resource(name = "sessionBean")
    private User mySessionBean;

    @Resource(name = "ConnectedUserBean")
    private Connected connectedUsers; //all connected users. in application scope

    @Autowired
    private MessageRepository db;

    //the url that handles the logging out of the user.
    //it also redirects to the login page
    @GetMapping("/logout")
    public ModelAndView Logout(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("redirect:/");

        //this triggers the session listener that will remove this user from connected users list
        request.getSession().invalidate();
        return modelAndView;
    }


    //retusns a json with the list of connected users.
    //if the parameter "number" - is the number of connected users - it returns null (no need to update)
    @GetMapping("/connectedUsers/{number}")
    public @ResponseBody List<String> ConnectedUsers(@PathVariable Integer number, Model model) {
        if(connectedUsers.connectedCount()-1 == number)
            return null;

        List<String> copyOfConnected = new LinkedList<String>(connectedUsers.getConnected(mySessionBean.getName()));
        return copyOfConnected;
    }


    //returns the chatroom view.
    //the user is forworded to here also from: @PostMapping("/chatroom/sendMessage"), and that
    // is way it's a @RequestMapping and not @GetMapping
    @RequestMapping("/chatroom")
    public String ChatRoom(Message message, Model model) {
        model.addAttribute("name", mySessionBean.getName());
        return "chatroom";
    }

    //gets the message that is sent to the chatroom.
    // the @Valid annotation validates the parameters of the message.
    @PostMapping("/chatroom/sendMessage")
    public String SendMessage(@Valid Message message, BindingResult result, Model model) {
        if(result.hasErrors()){
            model.addAttribute("name", mySessionBean.getName());
            return "chatroom"; //forward:/chatroom
        }

        db.save(message);
            return "forward:/chatroom";
    }

    //return a json eith the last 5 messages.
    // just if there is a new message, checked by parameter "lastId"
    @GetMapping("/chatroom/getChat/{lastId}")
    public @ResponseBody List<Message> GetMessage(@PathVariable String lastId){
        if(db.findFirstByOrderByIdDesc() == null || db.findFirstByOrderByIdDesc().getId() <= Long.parseLong(lastId)) //no (new) message
            return null;

        List<Message> last5Messages = db.findFirst5ByOrderByIdDesc();
        Collections.reverse(last5Messages);//the messages are returned in a descending order, so revers them
        return last5Messages;
    }

    //returns the search page
    @GetMapping("/chatroom/search")
    public String Search(Model model){ return "search"; }

    //returns a json with all messages of "name"
    @GetMapping("/chatroom/search/name/{name}")
    public @ResponseBody List<Message> GetByName(@PathVariable("name") String name){
        if(name == null)
            return null;
        return db.findAllByName(name);
    }

    //returns jason with all messages containing the text
    @GetMapping("/chatroom/search/message/{text}")
    public @ResponseBody List<Message> GetByMessage(@PathVariable("text") String text){
        if(text == null)
            return null;
        return db.findAllByTextContains(text);
    }

}
