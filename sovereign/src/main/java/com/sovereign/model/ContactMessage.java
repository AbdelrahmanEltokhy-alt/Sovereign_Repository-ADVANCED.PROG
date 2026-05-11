package com.sovereign.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * ContactMessage — submitted via the contact/booking form.
 * Saved to DB so admin can read all messages from the dashboard.
 */
@Entity
@Table(name = "contact_messages")
public class ContactMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String email;

    @Column(length = 2000)
    private String message;

    private LocalDateTime createdAt;  // auto-set on save

    // ── Constructors ──────────────────────────────────────────────────────────

    public ContactMessage() {}

    public ContactMessage(String name, String email, String message) {
        this.name = name;
        this.email = email;
        this.message = message;
        this.createdAt = LocalDateTime.now();
    }

    // ── Getters & Setters ─────────────────────────────────────────────────────

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
