package com.sovereign.service;

import com.sovereign.model.Car;
import com.sovereign.model.CarDto;
import com.sovereign.model.WishlistItem;
import com.sovereign.repository.CarRepository;
import com.sovereign.repository.WishlistRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * WishlistService — business logic for saving/removing cars from a user's personal vault.
 */
@Service
public class WishlistService {

    private final WishlistRepository wishlistRepository;
    private final CarRepository carRepository;

    public WishlistService(WishlistRepository wishlistRepository,
                           CarRepository carRepository) {
        this.wishlistRepository = wishlistRepository;
        this.carRepository = carRepository;
    }

    /** Add a car to the user's wishlist (if not already saved). */
    public void addToWishlist(String username, Long carId) {
        if (wishlistRepository.findByUsernameAndCarId(username, carId).isEmpty()) {
            carRepository.findById(carId).ifPresent(car -> {
                wishlistRepository.save(new WishlistItem(username, car));
            });
        }
    }

    /** Remove a car from the user's wishlist. */
    @Transactional
    public void removeFromWishlist(String username, Long carId) {
        wishlistRepository.deleteByUsernameAndCarId(username, carId);
    }

    /** Get all cars in the user's wishlist as DTOs. */
    public List<CarDto> getWishlist(String username) {
        return wishlistRepository.findByUsername(username).stream()
                .map(item -> CarDto.from(item.getCar()))
                .collect(Collectors.toList());
    }

    /** Check if a car is in the user's wishlist. */
    public boolean isInWishlist(String username, Long carId) {
        return wishlistRepository.findByUsernameAndCarId(username, carId).isPresent();
    }
}
