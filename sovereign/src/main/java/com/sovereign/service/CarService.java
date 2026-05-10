package com.sovereign.service;

import com.sovereign.model.Car;
import com.sovereign.model.Mod;
import com.sovereign.repository.CarRepository;
import com.sovereign.repository.ModRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    private final CarRepository carRepository;
    private final ModRepository modRepository;

    public CarService(CarRepository carRepository, ModRepository modRepository) {
        this.carRepository = carRepository;
        this.modRepository = modRepository;
    }

    /** Return all cars for the showroom gallery. */
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    /** Return a single car by its database ID, or empty if not found. */
    public Optional<Car> getCarById(Long id) {
        return carRepository.findById(id);
    }

    /** Return all available luxury modifications. */
    public List<Mod> getAllMods() {
        return modRepository.findAll();
    }

    /** Return mods filtered by category (Performance, Interior, Exterior…). */
    public List<Mod> getModsByCategory(String category) {
        return modRepository.findByCategory(category);
    }
}
