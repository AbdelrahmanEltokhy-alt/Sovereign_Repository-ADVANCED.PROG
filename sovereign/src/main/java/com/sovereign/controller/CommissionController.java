package com.sovereign.controller;

import com.sovereign.model.Car;
import com.sovereign.model.CarColor;
import com.sovereign.model.Mod;
import com.sovereign.service.CarService;
import com.sovereign.service.ContactService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/commission")
public class CommissionController {

    private final CarService carService;
    private final ContactService contactService;

    public CommissionController(CarService carService, ContactService contactService) {
        this.carService = carService;
        this.contactService = contactService;
    }

    @GetMapping
    public String showReceipt(
            @RequestParam Long carId,
            @RequestParam(required = false) Long colorId,
            @RequestParam(required = false) List<Long> modIds,
            Model model) {

        Car car = carService.getCarById(carId).orElse(null);
        if (car == null) return "redirect:/cars";

        CarColor selectedColor = null;
        if (colorId != null) {
            selectedColor = car.getColors().stream()
                    .filter(c -> c.getId().equals(colorId))
                    .findFirst().orElse(null);
        }
        if (selectedColor == null && !car.getColors().isEmpty()) {
            selectedColor = car.getColors().get(0);
        }

        List<Mod> selectedMods = new ArrayList<>();
        double modsTotal = 0;
        if (modIds != null) {
            for (Long mid : modIds) {
                Optional<Mod> m = car.getMods().stream().filter(mod -> mod.getId().equals(mid)).findFirst();
                if (m.isPresent()) {
                    selectedMods.add(m.get());
                    modsTotal += m.get().getPrice();
                }
            }
        }

        double subtotal = car.getPrice() + modsTotal;
        double taxRate = 0.05; // 5% Luxury Tax
        double taxes = subtotal * taxRate;
        double total = subtotal + taxes;

        // ETA Logic
        int baseWeeks = 4;
        int modDays = selectedMods.size() * 4;
        if (car.getName().contains("Bespoke")) baseWeeks += 8;
        String eta = baseWeeks + " weeks and " + modDays + " days";

        model.addAttribute("car", car);
        model.addAttribute("color", selectedColor);
        model.addAttribute("mods", selectedMods);
        model.addAttribute("subtotal", subtotal);
        model.addAttribute("taxes", taxes);
        model.addAttribute("total", total);
        model.addAttribute("eta", eta);

        return "commission";
    }

    @PostMapping("/confirm")
    public String confirmCommission(
            @RequestParam String carName,
            @RequestParam String colorName,
            @RequestParam String modsList,
            @RequestParam String total,
            @RequestParam String email,
            @RequestParam String name) {

        String message = String.format(
                "COMMISSION REQUEST:\nCar: %s\nColor: %s\nMods: %s\nTotal Payment (incl. taxes): %s",
                carName, colorName, modsList, total
        );

        contactService.saveMessage(name, email, message);
        return "redirect:/contact?success=true";
    }
}
