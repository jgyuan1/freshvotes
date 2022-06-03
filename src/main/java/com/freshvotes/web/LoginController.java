package com.freshvotes.web;

import com.freshvotes.domain.User;
import com.freshvotes.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    //populate the model
    public String register(ModelMap modelMap) {
        modelMap.put("user", new User());
        return "register";
    }
    // The controller will handle the mapping of the endpoint and the method itself
    // and it also will handle the binding of the data
    @PostMapping("/register")
    //@ModelAttribute is not necessary, because thymeleaf has already done the work.
    public String registerPost(@ModelAttribute User user){
        // redirect will let the browser know you have already summit the form
        // and when you refresh the page, the register page will not appear again.
        // There will be no duplicate data.
        //System.out.println(user);
        //persist the registered user to the database
        userService.save(user);
        return "redirect:/login";
    }

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
    @GetMapping("/votes")
    public String votes() {
        return "votes";
    }
    @GetMapping("/logoutSuccessful")
    public String logout() {
        return "logoutSuccessful";
    }

}
