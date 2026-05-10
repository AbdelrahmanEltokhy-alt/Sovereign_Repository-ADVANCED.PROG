package com.sovereign.controller;

import com.sovereign.model.CarDto;
import com.sovereign.service.CarService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final CarService carService;

    public HomeController(CarService carService) {
        this.carService = carService;
    }

    /**
     * GET /
     * Renders the homepage with a featured selection of cars.
     */
    @GetMapping("/")
    public String home(Model model) {
        // Show up to 3 featured cars on the homepage hero section
        var cars = carService.getAllCars().stream()
                .limit(3)
                .map(CarDto::from)
                .collect(java.util.stream.Collectors.toList());
        model.addAttribute("featuredCars", cars);
        return "index";
    }
}
