package com.sovereign.service;

import com.sovereign.model.Car;
import com.sovereign.model.Mod;
import com.sovereign.repository.CarModRepository;
import com.sovereign.repository.CarRepository;
import com.sovereign.repository.ModRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// ─────────────────────────────────────────────
// LAYER   : Service
// PURPOSE : This is where ALL the business logic
//           for the configurator lives.
//           The Controller calls this class.
//           This class calls the Repositories.
//           Controller never touches the DB directly.
//
//           their Main jobs:
//           1. Get a car + all its available mods
//           2. Calculate total price from selected mods
//           3. Group mods by category for the UI
// ─────────────────────────────────────────────

@Service
public class ConfiguratorService {

    private final CarRepository carRepository;
    private final CarModRepository carModRepository;
    private final ModRepository modRepository;

    @Autowired
    private CarModRepository carModRepository;

    public ConfiguratorService(CarRepository carRepository,
                               CarModRepository carModRepository,
                               ModRepository modRepository) {
        this.carRepository = carRepository;
        this.carModRepository = carModRepository;
        this.modRepository = modRepository;
    }

    // ── 1. Get a car by ID ──────────────────────────────
    // Called by the controller to load the car details
    // Throws an exception if the car doesn't exist
    public Car getCarById(Long carId) {
        return carRepository.findById(carId)
                .orElseThrow(() -> new RuntimeException("Car not found: " + carId));
    }

    // ── 2. Get all mods for a specific car ──────────────
    // Uses CarModRepository to find mods linked to this car
    // Returns a flat list of Mod objects
    public List<Mod> getModsForCar(Long carId) {
        return carModRepository.findModsByCarId(carId);
    }

    // ── 3. Group mods by category ───────────────────────
    // Takes the flat list and organizes it into a Map:
    // { "Engine" → [mod1, mod2], "Exterior" → [mod3, mod4] }
    // Thymeleaf uses this to render mods in grouped sections
    public Map<String, List<Mod>> getModsGroupedByCategory(Long carId) {
        List<Mod> allMods = getModsForCar(carId);

        return allMods.stream()
                .collect(Collectors.groupingBy(Mod::getCategory));
    }

    // ── 4. Calculate total price ─────────────────────────
    // Base car price + sum of all selected mod prices
    // selectedModIds = the list of checkbox values the user submitted
    public double calculateTotal(Long carId, List<Long> selectedModIds) {
        Car car = getCarById(carId);
        double basePrice = car.getBasePrice();

        if (selectedModIds == null || selectedModIds.isEmpty()) {
            return basePrice;
        }

        // Fetch the actual mod objects from DB using their IDs
        List<Mod> selectedMods = modRepository.findByIdIn(selectedModIds);

        // Add up mod prices
        double modsTotal = selectedMods.stream()
                .mapToDouble(Mod::getPrice)
                .sum();

        return basePrice + modsTotal;
    }

    // ── 5. Validate mods belong to this car ─────────────
    // Safety check: make sure the user isn't submitting mod IDs
    // from a different car (basic data integrity)
    public boolean modsBelongToCar(Long carId, List<Long> selectedModIds) {
        List<Mod> carMods = getModsForCar(carId);

        List<Long> validIds = carMods.stream()
                .map(Mod::getId)
                .collect(Collectors.toList());

        return validIds.containsAll(selectedModIds);
    }
}
