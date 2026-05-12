package com.sovereign.repository;

import com.sovereign.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * CarRepository — Spring Data JPA gives us all CRUD methods for free.
 * Methods like findAll(), findById(), save(), count() are auto-generated.
 */
@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
}
