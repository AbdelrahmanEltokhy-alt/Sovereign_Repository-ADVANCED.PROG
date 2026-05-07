package org.springframework.boot;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int  id;

    private String username;
    private String email;
    private String pass;
    private String role;

    public User() {}

    public User(int id, String name, String email, String pass,String role) {
        super();
        this.id =id; 
        this.username = name;
        this.email = email;
        this.pass = pass;
        this.role= role ;
    }

    public long getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getusername() { return username; }

    public void setusername(String username) { this.username = username; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getPass() { return pass; }

    public void setPass(String pass) { this.pass = pass; }

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
    
}
