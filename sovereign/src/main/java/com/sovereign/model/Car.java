package com.sovereign.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String type; // e.g. "Grand Tourer", "Saloon", "SUV"

    @Column(length = 1000)
    private String description;

    @Column(nullable = false)
    private Double basePrice;

    private String imageUrl;

    @Column(length = 80)
    private String tagline; // short marketing line shown on the card

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<CarColor> colors = new ArrayList<>();

    public Car() {}

    public Car(String name, String type, String description, Double basePrice, String imageUrl, String tagline) {
        this.name = name;
        this.type = type;
        this.description = description;
        this.basePrice = basePrice;
        this.imageUrl = imageUrl;
        this.tagline = tagline;
    }

    // ── Getters & Setters ─────────────────────────────────────────────────────

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Double getBasePrice() { return basePrice; }
    public void setBasePrice(Double basePrice) { this.basePrice = basePrice; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public String getTagline() { return tagline; }
    public void setTagline(String tagline) { this.tagline = tagline; }

    public List<CarColor> getColors() { return colors; }
    public void setColors(List<CarColor> colors) { this.colors = colors; }

    // Helper to format price with commas for display in templates
    public String getFormattedPrice() {
        return basePrice != null ? String.format("%,.0f", basePrice) : "0";
    }
}
