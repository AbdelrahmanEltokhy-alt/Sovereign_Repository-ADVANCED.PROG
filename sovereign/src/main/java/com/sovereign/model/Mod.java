package com.sovereign.model;

import jakarta.persistence.*;

@Entity
@Table(name = "mods")
public class Mod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String category;    // e.g. "Performance", "Interior", "Exterior"

    @Column(nullable = false)
    private String name;        // e.g. "Carbon Fibre Aero Kit"

    @Column(length = 500)
    private String description;

    @Column(nullable = false)
    private Double price;       // additional cost on top of base price

    public Mod() {}

    public Mod(String category, String name, String description, Double price) {
        this.category = category;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    // ── Getters & Setters ─────────────────────────────────────────────────────

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public String getFormattedPrice() {
        return price != null ? String.format("%,.0f", price) : "0";
    }
}
