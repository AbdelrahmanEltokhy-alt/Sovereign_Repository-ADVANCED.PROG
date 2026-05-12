package com.sovereign.controller;

import com.sovereign.model.Car;
import com.sovereign.model.CarDto;
import com.sovereign.service.CarService;
import com.sovereign.service.ContactService;
import com.sovereign.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * AdminController — handles all /admin/** routes.
 *
 * Only accessible to users with the ADMIN role (enforced by SecurityConfig).
 * Provides dashboard, car CRUD management, message viewing, and user listing.
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    private final CarService carService;
    private final ContactService contactService;
    private final UserRepository userRepository;

    public AdminController(CarService carService,
                           ContactService contactService,
                           UserRepository userRepository) {
        this.carService = carService;
        this.contactService = contactService;
        this.userRepository = userRepository;
    }

    // ── GET /admin — Dashboard ──────────────────────────────────────────────

    @GetMapping
    public String dashboard(Model model) {
        model.addAttribute("cars", carService.getAllCars().stream()
                .map(CarDto::from).collect(Collectors.toList()));
        model.addAttribute("messages", contactService.getAllMessages());
        model.addAttribute("userCount", userRepository.count());
        return "admin/dashboard";
    }

    // ── GET /admin/cars — List all cars for management ───────────────────────

    @GetMapping("/cars")
    public String manageCars(Model model) {
        List<CarDto> cars = carService.getAllCars().stream()
                .map(CarDto::from)
                .collect(Collectors.toList());
        model.addAttribute("cars", cars);
        return "admin/manage-cars";
    }

    // ── GET /admin/cars/new — Show empty form to add a car ──────────────────

    @GetMapping("/cars/new")
    public String newCarForm(Model model) {
        model.addAttribute("car", new Car());
        return "admin/car-form";
    }

    // ── GET /admin/cars/{id}/edit — Show pre-filled form to edit a car ──────

    @GetMapping("/cars/{id}/edit")
    public String editCarForm(@PathVariable Long id, Model model) {
        Car car = carService.getCarById(id).orElse(new Car());
        model.addAttribute("car", car);
        return "admin/car-form";
    }

    // ── POST /admin/cars/save — Save new or update existing car ─────────────

    @PostMapping("/cars/save")
    public String saveCar(@ModelAttribute Car car) {
        carService.saveCar(car);
        return "redirect:/admin/cars";
    }

    // ── POST /admin/cars/{id}/delete — Delete a car ─────────────────────────

    @PostMapping("/cars/{id}/delete")
    public String deleteCar(@PathVariable Long id) {
        carService.deleteCar(id);
        return "redirect:/admin/cars";
    }

    // ── GET /admin/messages — View all contact messages ─────────────────────

    @GetMapping("/messages")
    public String viewMessages(Model model) {
        model.addAttribute("messages", contactService.getAllMessages());
        return "admin/messages";
    }

    // ── GET /admin/users — View all registered users ────────────────────────

    @GetMapping("/users")
    public String viewUsers(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "admin/users";
    }
}
