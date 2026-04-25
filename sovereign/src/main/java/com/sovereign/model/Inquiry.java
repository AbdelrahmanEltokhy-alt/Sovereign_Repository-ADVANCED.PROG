package com.sovereign.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Inquiry {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String email;
    private String category;
    private String message;

    public Inquiry(String name, String email, String category, String message){
        this.name = name;
        this.email = email;
        this.category = category;
        this.message = message;
    }

    public Long getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public String getEmail(){
        return email;
    }
    public String getCategory(){
        return category;
    }
    public String getMessage(){
        return message;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}
