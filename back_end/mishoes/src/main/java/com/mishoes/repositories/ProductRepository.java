package com.mishoes.repositories;

import com.mishoes.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

    boolean existsByName(String name);
    boolean existsByCode(String code);
}
