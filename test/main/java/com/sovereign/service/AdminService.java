package org.springframework.boot;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

	// check  of admin or user will  enter
public String checkUserRole(String email, String password) 
{      

	 if (email.equals("admin@gmail.com") && password.equals("123")) {
	        return "ADMIN";
	    }

	    return "USER";
	}

//make price not negative value
	public boolean isValidPrice(double price) {
	    return price > 0; 
	}
	
	 //  check availability of car (available or soldout)
	public CarRepository buyCar(CarRepository car) {

	    if (car.getStatus().equals("SOLD_OUT")) {
	        throw new RuntimeException("Car already sold");
	    }
	    else 
	    {
	    	System.out.println("this car are Available");  
	}
		return car;
		
	
	}

	public CarRepository addCar(CarRepository car) {
		// TODO Auto-generated method stub
		return null;
	}
	public <car> String getCarStatus(long id) {

	    CarRepository car =  CarRepository.findById(id).orElse(null);

	    if (car == null) {
	        return "Car not found";
	    }

	    if (car.Status() == true) {
	        return "AVAILABLE";
	    }

	    if (car.Status() == false) {
	        return "SOLD OUT";
	    }

	    return "UNKNOWN";
	}
	
}
