package com.mishoes.repositories;

import com.mishoes.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category , String > {
    boolean existsByCode(String code);
    boolean existsByName(String name);
}
