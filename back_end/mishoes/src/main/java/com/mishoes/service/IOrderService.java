package com.mishoes.service;

import com.mishoes.dto.request.create.order.CreateOrderRequest;
import com.mishoes.dto.request.update.order.UpdateOrderRequest;
import com.mishoes.dto.response.order.OrderResponse;

import java.util.List;

public interface IOrderService {
    List<OrderResponse> getOrders();

    OrderResponse getOrder(String orderId);

    OrderResponse createOrder(CreateOrderRequest request);

    OrderResponse updateOrder(String orderId, UpdateOrderRequest request);
    List<OrderResponse> getOrderByUserId (String userId);
    String deleteOrder(String orderId);
}
