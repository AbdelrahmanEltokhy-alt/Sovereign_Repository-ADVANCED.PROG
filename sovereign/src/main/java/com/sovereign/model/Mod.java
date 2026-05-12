package com.sovereign.model;

import jakarta.persistence.*;

/**
 * Mod — a luxury modification available for a specific Car.
 * Each Car has its own set of bespoke mods.
 */
@Entity
@Table(name = "mods")
public class Mod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;        // e.g. "Carbon Fibre Hood"

    @Column(length = 500)
    private String description; // e.g. "Full carbon weave replacing the factory bonnet"

    private double price;       // additional cost in USD

    private String category;    // e.g. "Exterior", "Performance", "Interior"

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id", nullable = false)
    private Car car;

    // ── Constructors ──────────────────────────────────────────────────────────

    public Mod() {}

    /** Used by DataInitializer: (name, description, price, category, car) */
    public Mod(String name, String description, double price, String category, Car car) {
        this.name        = name;
        this.description = description;
        this.price       = price;
        this.category    = category;
        this.car         = car;
    }

    // ── Getters & Setters ─────────────────────────────────────────────────────

    public Long   getId()          { return id; }
    public String getName()        { return name; }
    public String getDescription() { return description; }
    public double getPrice()       { return price; }
    public String getCategory()    { return category; }
    public Car    getCar()         { return car; }

    public void setId(Long id)               { this.id = id; }
    public void setName(String name)         { this.name = name; }
    public void setDescription(String desc)  { this.description = desc; }
    public void setPrice(double price)       { this.price = price; }
    public void setCategory(String category) { this.category = category; }
    public void setCar(Car car)              { this.car = car; }
}
