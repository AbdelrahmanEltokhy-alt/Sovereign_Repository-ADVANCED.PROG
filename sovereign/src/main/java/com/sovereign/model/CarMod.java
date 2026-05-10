package com.sovereign.model;

import jakarta.persistence.*;



@Entity
@Table(name = "car_mods")
public class CarMod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Which car this mod belongs to
    // ManyToOne = many CarMod rows can point to one Car
    @ManyToOne
    @JoinColumn(name = "car_id", nullable = false)
    private Car car;

    // Which mod it is
    // ManyToOne = many CarMod rows can point to one Mod
    @ManyToOne
    @JoinColumn(name = "mod_id", nullable = false)
    private Mod mod;

    // ── Constructors ──
    public CarMod() {}

    public CarMod(Car car, Mod mod) {
        this.car = car;
        this.mod = mod;
    }

    // ── Getters & Setters ──
    public Long getId()          { return id; }
    public void setId(Long id)  { this.id = id; }

    public Car getCar()          { return car; }
    public void setCar(Car car)  { this.car = car; }

    public Mod getMod()          { return mod; }
    public void setMod(Mod mod)  { this.mod = mod; }
}