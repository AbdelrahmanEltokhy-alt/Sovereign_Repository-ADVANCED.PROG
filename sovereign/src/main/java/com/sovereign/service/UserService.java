package com.sovereign.service;

import com.sovereign.model.User;
import com.sovereign.repository.UserRepository;
import org.springframework.security.crypto.password.passwordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;

    }

    public boolean register(String username, String email, String newPassword){

        if (userRepository.findByUsername(username).isPresent()){
            return false;
        }
        if (userRepository.findByEmail(email).isPresent()){
            return false;
        }
        User user = new User(username, email, passwordEncoder.encode(newPassword), "USER");
        userRepository.save(user);
        return true;
    }
}