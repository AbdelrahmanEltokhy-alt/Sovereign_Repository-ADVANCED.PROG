package com.sovereign.repository;

import com.sovereign.model.WishlistItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * WishlistRepository — data access for user wishlists.
 *
 * Spring Data JPA auto-generates queries from method names.
 * e.g. findByUsername(x) → SELECT * FROM wishlist_items WHERE username = x
 */
@Repository
public interface WishlistRepository extends JpaRepository<WishlistItem, Long> {

    /** All saved cars for a specific user. */
    List<WishlistItem> findByUsername(String username);

    /** Check if a specific car is already in the user's wishlist. */
    Optional<WishlistItem> findByUsernameAndCarId(String username, Long carId);

    /** Remove a car from the user's wishlist. */
    void deleteByUsernameAndCarId(String username, Long carId);
}
