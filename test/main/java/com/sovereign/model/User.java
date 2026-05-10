package org.springframework.boot;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;
    private String email;
    private String pass;

    public User() {}

    public User(int id, String username, String email, String pass) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.pass = pass;
    }

    public int getId() { return id;}
    public void setId(int id) { this.id = id;}

    
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username;}

    
    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}

    
    public String getPass() { return pass;}
    public void setPass(String pass) { this.pass = pass;}
