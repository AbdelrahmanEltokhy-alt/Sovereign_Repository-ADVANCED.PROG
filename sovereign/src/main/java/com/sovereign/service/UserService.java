package com.sovereign.service;

import com.sovereign.model.User;
import com.sovereign.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * UserService — handles registration logic.
 *
 * Spring Security handles login automatically once we configure it.
 * This service only needs to handle registration (saving a new user).
 *
 * PasswordEncoder is injected by Spring — it BCrypt-hashes passwords
 * before saving. Never store raw passwords in the DB.
 */
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // Both dependencies injected by Spring via constructor injection (Lect7)
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Registers a new user. Hashes their password before saving.
     * Returns false if the username or email is already taken.
     */
    public boolean register(String username, String email, String rawPassword) {
        if (userRepository.findByUsername(username).isPresent()) {
            return false; // username taken
        }
        if (userRepository.findByEmail(email).isPresent()) {
            return false; // email taken
        }
        User user = new User(username, email, passwordEncoder.encode(rawPassword), "USER");
        userRepository.save(user);
        return true;
    }
}
