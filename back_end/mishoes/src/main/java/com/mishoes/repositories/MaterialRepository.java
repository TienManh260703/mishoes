package com.mishoes.repositories;

import com.mishoes.models.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterialRepository extends JpaRepository<Material, String> {
    boolean existsByCode(String code);
    boolean existsByName(String name);
}
