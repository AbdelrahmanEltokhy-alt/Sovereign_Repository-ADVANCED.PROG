package com.sovereign.controller;

import com.sovereign.model.CarDto;
import com.sovereign.service.WishlistService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * WishlistController — lets logged-in users save and manage favourite cars.
 *
 * POST /wishlist/add/{carId}    → add car to wishlist
 * POST /wishlist/remove/{carId} → remove car from wishlist
 * GET  /wishlist                → show saved cars page
 */
@Controller
@RequestMapping("/wishlist")
public class WishlistController {

    private final WishlistService wishlistService;

    public WishlistController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    @PostMapping("/add/{carId}")
    public String addToWishlist(@PathVariable Long carId,
                                Authentication authentication,
                                @RequestHeader(value = "Referer", required = false) String referer) {
        if (authentication == null) return "redirect:/user/login";
        wishlistService.addToWishlist(authentication.getName(), carId);
        return "redirect:" + (referer != null ? referer : "/cars");
    }

    @PostMapping("/remove/{carId}")
    public String removeFromWishlist(@PathVariable Long carId,
                                     Authentication authentication,
                                     @RequestHeader(value = "Referer", required = false) String referer) {
        if (authentication == null) return "redirect:/user/login";
        wishlistService.removeFromWishlist(authentication.getName(), carId);
        return "redirect:" + (referer != null ? referer : "/wishlist");
    }

    @GetMapping
    public String showWishlist(Authentication authentication, Model model) {
        if (authentication == null) return "redirect:/user/login";
        String username = authentication.getName();
        List<CarDto> cars = wishlistService.getWishlist(username);
        model.addAttribute("cars", cars);
        return "user/wishlist";
    }
}
