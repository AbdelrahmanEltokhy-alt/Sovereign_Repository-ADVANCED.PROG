package org.springframework.boot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

	@Autowired
    private CarRepository carRepository;
	
	
    public boolean checkAdmin(String password) {
        return password != null && password.equals("12345");
    }

    public boolean checkUserLogin(String email, String password) {
        return email != null && password != null && !password.isEmpty();
    }

    public boolean isValidPrice(int price) {
        return price > 0;
    }

    public CarRepository buyCar(CarRepository car) {
            
    	 if(car.getStatus() == null) {
            throw new RuntimeException("Car already sold");
        }

        return car;
    }

    @Autowired
    private CarRepository repo;

    public Car addCar(Car car) {

        if (car.getPrice() <= 0) {
            System.out.println("Enter price with positive value");
            return null;
        }

        return repo.save(car);
    }

    public String getCarStatus(CarRepository car) {

        if (car == null) {
            return "Car not found";
        }

        if (car.getStatus() != null) {
            return "AVAILABLE";
        } else {
            return "SOLD OUT";
        }
    }
}
