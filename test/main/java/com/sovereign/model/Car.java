package org.springframework.boot;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "car details")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true)
    private String name;
    private String model;
    private int price;
    private boolean Status;

    public Car() {
    }

    public Car(Long id, String name, String model, int price, boolean Status) {
        this.id = id;
        this.name = name;
        this.model = model;
        this.price = price;
        this.Status = Status;
    }

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public String getModel() {return model;}
    public void setModel(String model) {this.model = model;}
    
    public int getPrice() {return price;}
    public void setPrice(int price) {this.price = price;}
    
    public boolean isStatus() {return Status;}
    public void setStatus(boolean Status) {this.Status = Status;}
}