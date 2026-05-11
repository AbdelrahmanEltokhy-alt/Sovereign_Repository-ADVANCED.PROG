package com.sovereign.service;

import com.sovereign.model.Car;
import com.sovereign.model.Mod;
import com.sovereign.repository.CarModRepository;
import com.sovereign.repository.CarRepository;
import com.sovereign.repository.ModRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ConfiguratorService {

    private final CarRepository carRepository;
    private final CarModRepository carModRepository;
    private final ModRepository modRepository;


    public ConfiguratorService(CarRepository carRepository,
                               CarModRepository carModRepository,
                               ModRepository modRepository) {
        this.carRepository = carRepository;
        this.carModRepository = carModRepository;
        this.modRepository = modRepository;
    }

    public Car getCarById(Long carId) {
        return carRepository.findById(carId)
                .orElseThrow(() -> new RuntimeException("Car not found: " + carId));
    }

    public List<Mod> getModsForCar(Long carId) {
        return carModRepository.findModsByCarId(carId);
    }

    public Map<String, List<Mod>> getModsGroupedByCategory(Long carId) {
        List<Mod> allMods = getModsForCar(carId);

        return allMods.stream()
                .collect(Collectors.groupingBy(Mod::getCategory));
    }

    public double calculateTotal(Long carId, List<Long> selectedModIds) {
        Car car = getCarById(carId);
        double basePrice = car.getBasePrice();

        if (selectedModIds == null || selectedModIds.isEmpty()) {
            return basePrice;
        }

        List<Mod> selectedMods = modRepository.findByIdIn(selectedModIds);

        double modsTotal = selectedMods.stream()
                .mapToDouble(Mod::getPrice)
                .sum();

        return basePrice + modsTotal;
    }

    public boolean modsBelongToCar(Long carId, List<Long> selectedModIds) {
        List<Mod> carMods = getModsForCar(carId);

        List<Long> validIds = carMods.stream()
                .map(Mod::getId)
                .collect(Collectors.toList());

        return validIds.containsAll(selectedModIds);
    }
}