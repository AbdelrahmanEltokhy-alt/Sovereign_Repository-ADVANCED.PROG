package com.sovereign.controller;

import com.sovereign.model.CarDto;
import com.sovereign.service.CarService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

/**
 * HomeController — serves the main landing page at GET /.
 *
 * Passes up to 3 featured cars to the homepage hero section.
 */
@Controller
public class HomeController {

    private final CarService carService;

    public HomeController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/")
    public String home(Model model) {
        // Take the first 3 cars from the DB for the featured section
        List<CarDto> featured = carService.getAllCars().stream()
                .limit(3)
                .map(CarDto::from)
                .collect(Collectors.toList());

        model.addAttribute("featuredCars", featured);
        return "index";   // → templates/index.html
    }
}
