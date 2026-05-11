package com.sovereign.controller;

import com.sovereign.model.Car;
import com.sovereign.model.Mod;
import com.sovereign.service.ConfiguratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

// ─────────────────────────────────────────────
// LAYER   : Controller
// PURPOSE : Handles HTTP requests from the browser.
//           It is the TRAFFIC COP of your app.
//           Browser sends a request →
//           Controller receives it →
//           Controller calls Service →
//           Service returns data →
//           Controller puts data in Model →
//           Thymeleaf renders the HTML page.
//           Controller does NOT do logic.
//           Controller does NOT touch the DB.
//           It only coordinates.
// ─────────────────────────────────────────────

@Controller
@RequestMapping("/configurator")
public class ConfiguratorController {

    // Spring injects the service automatically
    @Autowired
    private ConfiguratorService configuratorService;

    // ── GET /configurator/{carId} ────────────────────────
    // Loads the configurator page for a specific car.
    // Example: user visits /configurator/1 → sees Regent's mods
    //
    // "Model model" is NOT a car model — it's Spring's way
    // of passing data to the Thymeleaf HTML template.
    // model.addAttribute("key", value) → use ${key} in HTML
    @GetMapping("/{carId}")
    public String showConfigurator(@PathVariable Long carId, Model model) {

        // 1. Get the car from DB via service
        Car car = configuratorService.getCarById(carId);

        // 2. Get mods grouped by category for clean UI rendering
        Map<String, List<Mod>> modsByCategory =
                configuratorService.getModsGroupedByCategory(carId);

        // 3. Put everything into the model so Thymeleaf can use it
        model.addAttribute("car", car);
        model.addAttribute("modsByCategory", modsByCategory);
        model.addAttribute("basePrice", car.getBasePrice());

        // 4. Tell Spring which HTML template to render
        // → looks for: src/main/resources/templates/configurator.html
        return "configurator";
    }

    // ── POST /configurator/{carId}/calculate ────────────
    // Called by JavaScript (fetch/AJAX) when user
    // checks/unchecks mods. Returns the new total price.
    // @ResponseBody means return plain text/JSON, not a page.
    @PostMapping("/{carId}/calculate")
    @ResponseBody
    public double calculatePrice(@PathVariable Long carId,
                                 @RequestBody(required = false) List<Long> selectedModIds) {

        // Validate mods actually belong to this car
        if (selectedModIds != null && !selectedModIds.isEmpty()) {
            if (!configuratorService.modsBelongToCar(carId, selectedModIds)) {
                throw new RuntimeException("Invalid mod selection");
            }
        }

        // Return the calculated total price
        return configuratorService.calculateTotal(carId, selectedModIds);
    }

    // ── POST /configurator/{carId}/save ─────────────────
    // When user clicks "Save Configuration" or "Add to Wishlist"
    // Member 6 (Wishlist) will expand this endpoint later.
    // For now it just redirects back to the configurator page.
    @PostMapping("/{carId}/save")
    public String saveConfiguration(@PathVariable Long carId,
                                    @RequestParam(required = false) List<Long> selectedModIds,
                                    Model model) {

        double total = configuratorService.calculateTotal(carId, selectedModIds);

        Car car = configuratorService.getCarById(carId);

        model.addAttribute("car", car);
        model.addAttribute("modsByCategory",
                configuratorService.getModsGroupedByCategory(carId));
        model.addAttribute("basePrice",
                configuratorService.getCarById(carId).getBasePrice());
        model.addAttribute("savedTotal", total);
        model.addAttribute("savedMods", selectedModIds);
        model.addAttribute("successMessage",
                "Configuration saved! Total: $" + String.format("%,.0f", total));

        return "configurator";
    }
}
