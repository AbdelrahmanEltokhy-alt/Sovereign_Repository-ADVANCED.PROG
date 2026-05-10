package com.sovereign.model;

import jakarta.persistence.*;

@Entity
@Table(name = "car_colors")
public class CarColor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;       // e.g. "Midnight Obsidian"

    @Column(nullable = false)
    private String hexCode;    // e.g. "#1A1A2E"

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id", nullable = false)
    private Car car;

    public CarColor() {}

    public CarColor(String name, String hexCode, Car car) {
        this.name = name;
        this.hexCode = hexCode;
        this.car = car;
    }

    // ── Getters & Setters ─────────────────────────────────────────────────────

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getHexCode() { return hexCode; }
    public void setHexCode(String hexCode) { this.hexCode = hexCode; }

    public Car getCar() { return car; }
    public void setCar(Car car) { this.car = car; }
}
