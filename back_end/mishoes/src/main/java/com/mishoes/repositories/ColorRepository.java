package com.mishoes.repositories;

import com.mishoes.models.Color;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColorRepository extends JpaRepository<Color , String > {
    boolean existsByCode(String code);
    boolean existsByName(String name);

}
