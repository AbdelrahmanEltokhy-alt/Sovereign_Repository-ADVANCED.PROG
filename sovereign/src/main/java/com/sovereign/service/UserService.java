package com.sovereign.service;

import com.sovereign.model.User;
import com.sovereign.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // ── Spring Security calls this on every login ──
    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPasswordHash())
                .roles(user.getRole() != null ? user.getRole() : "USER")
                .build();
    }

    // ── Register a new user ──
    public boolean register(String username, String email, String newPassword) {
        if (userRepository.findByUsername(username).isPresent()) {
            return false;
        }
        if (userRepository.findByEmail(email).isPresent()) {
            return false;
        }
        User user = new User(username, email, passwordEncoder.encode(newPassword), "USER");
        userRepository.save(user);
        return true;
    }
}