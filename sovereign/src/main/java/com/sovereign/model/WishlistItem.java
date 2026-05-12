package com.sovereign.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * WishlistItem — links a user to a saved car.
 * Users can "bookmark" cars they are interested in.
 * Uses username (String) rather than a User FK for simplicity with Spring Security.
 */
@Entity
@Table(name = "wishlist_items")
public class WishlistItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;  // the logged-in user's username

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id", nullable = false)
    private Car car;

    private LocalDateTime addedAt;

    // ── Constructors ──────────────────────────────────────────────────────────

    public WishlistItem() {}

    public WishlistItem(String username, Car car) {
        this.username = username;
        this.car = car;
        this.addedAt = LocalDateTime.now();
    }

    // ── Getters & Setters ─────────────────────────────────────────────────────

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public Car getCar() { return car; }
    public void setCar(Car car) { this.car = car; }

    public LocalDateTime getAddedAt() { return addedAt; }
    public void setAddedAt(LocalDateTime addedAt) { this.addedAt = addedAt; }
}
