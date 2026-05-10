package com.sovereign.repository;

import com.sovereign.model.Mod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ModRepository extends JpaRepository<Mod, Long> {
    List<Mod> findByCategory(String category);
}
