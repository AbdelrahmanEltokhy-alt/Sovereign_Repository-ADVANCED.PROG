package org.springframework.boot;

import java.util.List;

import org.springframework.stereotype.Repository;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.annotation.Generated;

@Entity
@Repository
public class CarRepository {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)

private long id;
public String name ; 
public String model ;
public int price ; 
public boolean status ; 
	
	public CarRepository() {}

	public CarRepository(  long id,String name, String model,int price, boolean status) {
		super();
		this.id=id ; 
		this.name = name;
		this.model=model;
		this.price = price;
		this.status = status;
	}
  
	
	
	public long getId() {return id;}

	public void setId(long id) {this.id = id;}

	public String getName() {return name;}

	public void setName(String name) {this.name = name;}
	

	public String getModel() {return model;}
	
	public void setModel(String model) {this.model = model;}
	

	public int getPrice() {	return price;}

	public void setPrice(int price) {this.price = price;}

	public boolean Status() {return status;}

	public void setStatus(boolean status) {this.status = status;}
	
	
public static List<CarRepository> findAll()
{}

public String getStatus() {
	// TODO Auto-generated method stub
	return null;
}

public static Object findById(long id2) {
	// TODO Auto-generated method stub
	return null;
}
	
	
	
}
