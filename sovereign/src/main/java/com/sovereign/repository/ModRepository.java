package com.sovereign.repository;

import com.sovereign.model.Mod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;



// PURPOSE : Handles all database queries for Mod.
//           JpaRepository gives you save(), findById(),
//           findAll(), deleteById() for FREE —
//           you don't write SQL yourself.
//


@Repository
public interface ModRepository extends JpaRepository<Mod, Long> {

    // Custom query: find all mods that belong to a specific category
    // Spring auto-generates the SQL from the method name
    // → SELECT * FROM mods WHERE category = ?
    List<Mod> findByCategory(String category);

    // Find mods by a list of IDs
    // Used when the user submits their selected mod checkboxes
    // → SELECT * FROM mods WHERE id IN (?, ?, ?)
    List<Mod> findByIdIn(List<Long> ids);
}