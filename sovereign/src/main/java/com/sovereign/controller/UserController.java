package com.sovereign.controller;

import com.sovereign.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/user")

public class UserController {
    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    
    }

    @GetMapping("/register")
    public String showRegisterForm(){
        return "user/register";
    }

    @PostMapping("/register")
    public String processRegister(@RequestParam String username, @RequestParam String email, @RequestParam String passwordString
        , Model model){
            boolean success = userService.register(username, email, passwordString);
            if(!success){
                model.addAttribute("error", "Username or email taken already");
                return "user/register";
            }
            return "redirect:/login";
        }

    @GetMapping("/login")
    public String showLoginForm(){
        return "user/login";
    }    
}