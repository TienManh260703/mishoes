package com.mishoes.repository;

import com.mishoes.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<Brand, String> {
    boolean existsByCode(String code);
    boolean existsByName(String name);
}
