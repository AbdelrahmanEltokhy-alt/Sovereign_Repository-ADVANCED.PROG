package org.springframework.boot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

	@Autowired
    private CarRepository carRepository;
	
	    // check validity of admin
    public boolean checkAdmin(String password) {
        return password != null && password.equals("12345");
    }
                                   // check user validity
    public boolean checkUserLogin(String email, String password) {
        return email != null && password != null && !password.isEmpty();
    }
     // price validity(price not less than zero)
    public boolean isValidPrice(int price) {
        return price > 0;
    }
      
    public CarRepository buyCar(CarRepository car) {

        if (!car.getStatus()) {
            throw new RuntimeException("Car already sold");
        }
               
        return car;      // when car available
    }
    // when admin add car price must be write price not negative
    public CarRepository addCar(CarRepository car) {

        if (car.getPrice() <= 0) {
            throw new RuntimeException("Price must be positive");
        }

        return car;
    }
                               // car status(available or soldout)
    public String getCarStatus(CarRepository car) {

        if (car == null) {
            return "sold out";
        }

        if (car.getStatus()) {
            return "AVAILABLE";
        } else {
            return "SOLD OUT";
        }
    }
}
