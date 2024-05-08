package com.mishoes.repositories;

import com.mishoes.models.Sole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SoleRepository extends JpaRepository<Sole, String> {

    boolean existsByCode(String code);

    boolean existsByName(String name);

}
