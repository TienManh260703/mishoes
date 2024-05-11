package com.mishoes.repository;

import com.mishoes.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

    boolean existsByName(String name);
    boolean existsByCode(String code);
}
