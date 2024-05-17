package com.mishoes.repository;

import com.mishoes.entity.Order;
import com.mishoes.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface OrderRepository extends JpaRepository<Order, String> {
    List<Order> findOrderByUserId(User userId);

}
