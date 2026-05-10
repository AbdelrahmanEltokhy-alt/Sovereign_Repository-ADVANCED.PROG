package com.sovereign.repository;

import com.sovereign.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    // Spring Data JPA auto-generates: findAll(), findById(), save(), deleteById()...
}
