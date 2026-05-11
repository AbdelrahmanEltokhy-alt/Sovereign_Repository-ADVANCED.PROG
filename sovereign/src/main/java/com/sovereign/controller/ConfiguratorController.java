package com.sovereign.controller;

import com.sovereign.model.Car;
import com.sovereign.model.Mod;
import com.sovereign.service.ConfiguratorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/configurator")
public class ConfiguratorController {

    private final ConfiguratorService configuratorService;

    public ConfiguratorController(ConfiguratorService configuratorService) {
        this.configuratorService = configuratorService;
    }

    @GetMapping("/{carId}")
    public String showConfigurator(@PathVariable Long carId, Model model) {

        Car car = configuratorService.getCarById(carId);

        Map<String, List<Mod>> modsByCategory =
                configuratorService.getModsGroupedByCategory(carId);

        model.addAttribute("car", car);
        model.addAttribute("modsByCategory", modsByCategory);
        model.addAttribute("basePrice", car.getBasePrice());

        return "configurator";
    }

    @PostMapping("/{carId}/calculate")
    @ResponseBody
    public double calculatePrice(@PathVariable Long carId,
                                 @RequestBody(required = false) List<Long> selectedModIds) {

        if (selectedModIds != null && !selectedModIds.isEmpty()) {
            if (!configuratorService.modsBelongToCar(carId, selectedModIds)) {
                throw new RuntimeException("Invalid mod selection");
            }
        }

        return configuratorService.calculateTotal(carId, selectedModIds);
    }

    @PostMapping("/{carId}/save")
    public String saveConfiguration(@PathVariable Long carId,
                                    @RequestParam(required = false) List<Long> selectedModIds,
                                    Model model) {

        double total = configuratorService.calculateTotal(carId, selectedModIds);

        Car car = configuratorService.getCarById(carId);

        model.addAttribute("car", car);
        model.addAttribute("modsByCategory",
                configuratorService.getModsGroupedByCategory(carId));
        model.addAttribute("basePrice", car.getBasePrice());
        model.addAttribute("savedTotal", total);
        model.addAttribute("savedMods", selectedModIds);
        model.addAttribute("successMessage",
                "Configuration saved! Total: $" + String.format("%,.0f", total));

        return "configurator";
    }
}