package com.sovereign.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Car — represents a Sovereign motor car in the showroom.
 * Each car has a set of available colours (CarColor) and
 * optional luxury modifications (Mod).
 */
@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;        // e.g. "Sovereign Phantom"

    @Column(nullable = false)
    private String model;       // e.g. "Phantom"

    @Column(name = "manufacture_year")
    private int year;           // e.g. 2025

    private double price;       // base price in USD

    @Column(length = 1000)
    private String description;

    private String imageUrl;    // Unsplash CDN URL or local /images/...

    // ── Relationships ─────────────────────────────────────────────────────────

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CarColor> colors = new ArrayList<>();

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Mod> mods = new ArrayList<>();

    // ── Constructors ──────────────────────────────────────────────────────────

    public Car() {
    }

    /** Used by DataInitializer: (name, model, year, price, description, imageUrl) */
    public Car(String name, String model, int year, double price,
            String description, String imageUrl) {
        this.name = name;
        this.model = model;
        this.year = year;
        this.price = price;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    // ── Getters & Setters ─────────────────────────────────────────────────────

    public Long   getId()          { return id; }
    public String getName()        { return name; }
    public String getModel()       { return model; }
    public int    getYear()        { return year; }
    public double getPrice()       { return price; }
    public String getDescription() { return description; }
    public String getImageUrl()    { return imageUrl; }
    public List<CarColor> getColors() { return colors; }
    public List<Mod>      getMods()   { return mods; }

    public void setId(Long id)               { this.id = id; }
    public void setName(String name)         { this.name = name; }
    public void setModel(String model)       { this.model = model; }
    public void setYear(int year)            { this.year = year; }
    public void setPrice(double price)       { this.price = price; }
    public void setDescription(String d)     { this.description = d; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
}
