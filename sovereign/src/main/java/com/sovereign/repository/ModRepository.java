package com.sovereign.repository;

import com.sovereign.model.Mod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/** ModRepository — CRUD for luxury modifications. */
@Repository
public interface ModRepository extends JpaRepository<Mod, Long> {

    /** Returns all mods for a specific car (used in detail page). */
    List<Mod> findByCarId(Long carId);
}
