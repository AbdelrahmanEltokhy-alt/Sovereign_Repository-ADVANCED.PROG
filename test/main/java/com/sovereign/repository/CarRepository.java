package org.springframework.boot;

import java.util.Optional;  // to avoid nullpointerExcepetion  .

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

	 
    Optional<Car> findById(Long id);

	Car getStatus();
	Car getPrice();

}