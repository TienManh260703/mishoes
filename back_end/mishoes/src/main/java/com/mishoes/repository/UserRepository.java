package com.mishoes.repository;

import com.mishoes.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    boolean existsByUserName(String s);

    boolean existsByPhone(String s);

    Optional<User> findByUserName(String username);
}
