package com.sovereign.service;

import com.sovereign.model.Car;
import com.sovereign.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * CarService — business logic layer for the showroom.
 *
 * Controllers should call this, not touch the repository directly.
 * This keeps the architecture clean and testable.
 */
@Service
public class CarService {

    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    /** All cars for the gallery page. */
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    /** Single car by ID for the detail page. Returns empty if not found. */
    public Optional<Car> getCarById(Long id) {
        return carRepository.findById(id);
    }

    /** Save a new car or update an existing one (used by admin). */
    public Car saveCar(Car car) {
        return carRepository.save(car);
    }

    /** Delete a car by its ID (used by admin). */
    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }

    /** Count total cars (used by admin dashboard). */
    public long countCars() {
        return carRepository.count();
    }
}
