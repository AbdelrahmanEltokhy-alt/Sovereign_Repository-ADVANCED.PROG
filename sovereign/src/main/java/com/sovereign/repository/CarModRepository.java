package com.sovereign.repository;

import com.sovereign.model.CarMod;
import com.sovereign.model.Mod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

// ─────────────────────────────────────────────
// LAYER   : Repository
// PURPOSE : Handles database queries for CarMod
//           (the join table between Car and Mod).
//           Main job: given a car ID, get all
//           the mods that belong to that car.
// ─────────────────────────────────────────────

@Repository
public interface CarModRepository extends JpaRepository<CarMod, Long> {

    // Get all CarMod rows where car_id matches
    // → SELECT * FROM car_mods WHERE car_id = ?
    List<CarMod> findByCar_Id(Long carId);

    // Custom JPQL query: get just the Mod objects directly
    // for a given car — cleaner to work with in the service
    // → SELECT cm.mod FROM car_mods WHERE cm.car.id = :carId
    @Query("SELECT cm.mod FROM CarMod cm WHERE cm.car.id = :carId")
    List<Mod> findModsByCarId(@Param("carId") Long carId);
}
