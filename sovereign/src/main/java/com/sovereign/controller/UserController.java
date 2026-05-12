package com.sovereign.controller;

import com.sovereign.model.User;
import com.sovereign.repository.UserRepository;
import com.sovereign.service.UserService;
import org.springframework.security.core.Authentication;
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
    private final UserRepository userRepository;

    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @GetMapping("/register")
    public String showRegisterForm() {
        return "user/register";
    }

    @PostMapping("/register")
    public String processRegister(@RequestParam String username,
                                  @RequestParam String email,
                                  @RequestParam String password,
                                  Model model) {
        boolean success = userService.register(username, email, password);
        if (!success) {
            model.addAttribute("error", "Username or email already taken.");
            return "user/register";
        }
        return "redirect:/user/login";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "user/login";
    }

    /**
     * Profile page — loads the logged-in user's details from DB.
     * Authentication is injected by Spring Security automatically.
     */
    @GetMapping("/profile")
    public String showProfile(Authentication authentication, Model model) {
        if (authentication == null) return "redirect:/user/login";

        String username = authentication.getName();
        userRepository.findByUsername(username).ifPresent(user -> {
            model.addAttribute("user", user);
        });

        return "user/profile";
    }
}
