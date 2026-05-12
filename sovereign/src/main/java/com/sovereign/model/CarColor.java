package com.sovereign.model;

import jakarta.persistence.*;

/**
 * CarColor — one of the available paint colours for a Car.
 * Each Car can have multiple CarColor options.
 */
@Entity
@Table(name = "car_colors")
public class CarColor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;     // e.g. "Midnight Black"

    @Column(nullable = false)
    private String hexCode;  // e.g. "#0D0D0D"

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id", nullable = false)
    private Car car;

    // ── Constructors ──────────────────────────────────────────────────────────

    public CarColor() {}

    /** Used by DataInitializer: (name, hexCode, car) */
    public CarColor(String name, String hexCode, Car car) {
        this.name    = name;
        this.hexCode = hexCode;
        this.car     = car;
    }

    // ── Getters & Setters ─────────────────────────────────────────────────────

    public Long   getId()      { return id; }
    public String getName()    { return name; }
    public String getHexCode() { return hexCode; }
    public Car    getCar()     { return car; }

    public void setId(Long id)         { this.id = id; }
    public void setName(String name)   { this.name = name; }
    public void setHexCode(String hex) { this.hexCode = hex; }
    public void setCar(Car car)        { this.car = car; }
}
