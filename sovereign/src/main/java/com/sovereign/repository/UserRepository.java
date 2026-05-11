package com.sovereign.repository;

import com.sovereign.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

/**
 * UserRepository — data access for user accounts.
 *
 * findByUsername() is used by Spring Security during login.
 * Optional<User> means "might return a User, might return empty" — 
 * safer than returning null.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
}
