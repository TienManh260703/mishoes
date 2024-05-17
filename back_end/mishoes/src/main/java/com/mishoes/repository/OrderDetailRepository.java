package com.mishoes.repository;

import com.mishoes.entity.Order;
import com.mishoes.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, String> {
    List<OrderDetail> findOrderDetailByOrderId(Order orderId);
}
