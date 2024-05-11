package com.mishoes.repository;

import com.mishoes.entity.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SizeRepository extends JpaRepository<Size, String> {

    boolean existsByCode(String code);

    boolean existsByName(String name);

}
