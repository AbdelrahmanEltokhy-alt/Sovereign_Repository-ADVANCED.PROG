package com.sovereign.controller;

import com.sovereign.model.Car;
import com.sovereign.model.CarDto;
import com.sovereign.service.CarService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * CarController — handles the public showroom routes.
 *
 * GET /cars        → catalog.html  (grid of all models)
 * GET /cars/{id}   → detail.html   (single car with colours + mods)
 */
@Controller
@RequestMapping("/cars")
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    // ── GET /cars — showroom gallery ─────────────────────────────────────────

    @GetMapping
    public String catalog(Model model) {
        List<CarDto> cars = carService.getAllCars().stream()
                .map(CarDto::from)
                .collect(Collectors.toList());
        model.addAttribute("cars", cars);
        return "cars/catalog";   // → templates/cars/catalog.html
    }

    // ── GET /cars/{id} — car detail page ─────────────────────────────────────

    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        Optional<Car> carOpt = carService.getCarById(id);

        if (carOpt.isEmpty()) {
            return "redirect:/cars";   // Car not found → back to gallery
        }

        // Convert JPA entity to DTO — prevents Hibernate proxy / Thymeleaf 3.1 issues
        CarDto carDto = CarDto.from(carOpt.get());

        model.addAttribute("car", carDto);
        return "cars/detail";   // → templates/cars/detail.html
    }
}
