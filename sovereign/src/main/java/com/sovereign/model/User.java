package com.sovereign.model;

import jakarta.persistence.*;

/**
 * User — a registered account on the site.
 *
 * role is either "USER" or "ADMIN".
 * Spring Security reads this role to decide what the user can access.
 * e.g. only ADMIN can reach /admin/** routes.
 *
 * passwordHash stores the BCrypt-hashed password — NEVER store plain text.
 * Spring Security's PasswordEncoder handles hashing automatically.
 */
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String passwordHash;  // BCrypt hash, never plain text

    private String role;          // "USER" or "ADMIN"

    // ── Constructors ──────────────────────────────────────────────────────────

    public User() {}

    public User(String username, String email, String passwordHash, String role) {
        this.username = username;
        this.email = email;
        this.passwordHash = passwordHash;
        this.role = role;
    }

    // ── Getters & Setters ─────────────────────────────────────────────────────

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPasswordHash() { return passwordHash; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}
