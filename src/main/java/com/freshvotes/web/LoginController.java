package com.freshvotes.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "login";
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
