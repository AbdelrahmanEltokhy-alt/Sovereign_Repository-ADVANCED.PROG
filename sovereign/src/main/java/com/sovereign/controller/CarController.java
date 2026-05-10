package com.sovereign.controller;

import com.sovereign.model.Car;
import com.sovereign.model.CarDto;
import com.sovereign.model.CarDto.ModDto;
import com.sovereign.service.CarService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    /** GET /catalog — renders the showroom gallery */
    @GetMapping("/catalog")
    public String catalog(Model model) {
        List<CarDto> cars = carService.getAllCars().stream()
                .map(CarDto::from)
                .collect(Collectors.toList());
        model.addAttribute("cars", cars);
        return "catalog";
    }

    /** GET /catalog/{id} — renders the individual car detail page */
    @GetMapping("/catalog/{id}")
    public String carDetail(@PathVariable Long id, Model model) {
        Optional<Car> carOpt = carService.getCarById(id);
        if (carOpt.isEmpty()) {
            return "redirect:/catalog";
        }

        // Convert JPA entity to plain DTO — avoids Hibernate proxy + Thymeleaf 3.1 issues
        CarDto carDto = CarDto.from(carOpt.get());

        List<ModDto> modDtos = carService.getAllMods().stream()
                .map(m -> new ModDto(m.getId(), m.getCategory(), m.getName(),
                                     m.getDescription(), m.getPrice()))
                .collect(Collectors.toList());

        model.addAttribute("car", carDto);
        model.addAttribute("mods", modDtos);
        return "detail";
    }
}
