package com.mishoes.service.iplm.order;

import com.mishoes.dto.request.create.order.CreateOrderRequest;
import com.mishoes.dto.request.update.order.UpdateOrderRequest;
import com.mishoes.dto.response.order.OrderResponse;
import com.mishoes.entity.Order;
import com.mishoes.entity.User;
import com.mishoes.enums.EnumStatus;
import com.mishoes.exception.AppException;
import com.mishoes.exception.DataNotFoundException;
import com.mishoes.exception.ErrorCode;
import com.mishoes.mapper.order.OrderMapper;
import com.mishoes.repository.OrderRepository;
import com.mishoes.repository.UserRepository;
import com.mishoes.service.IOrderService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static com.mishoes.common.GenCode.generateORDER;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderService implements IOrderService {
    UserRepository userRepository;
    OrderRepository orderRepository;
    OrderMapper orderMapper;

    @Override
    public List<OrderResponse> getOrders() {
        return orderRepository.findAll().stream().map(
                order -> orderMapper.toOrderResponse(order))
                .toList();
    }

    @Override
    public OrderResponse getOrder(String orderId) {
        return orderMapper.toOrderResponse(orderRepository.findById(orderId).orElseThrow(() -> {
            throw new DataNotFoundException("Order not found");
        }));
    }

    @Override
    public OrderResponse createOrder(CreateOrderRequest request) {
        User existingUser = userRepository.findById(request.getUserId()).orElseThrow(() -> {
            throw new DataNotFoundException("User not found");
        });
        Order order = orderMapper.createOrder(request);
        order.setCode(generateORDER());
        order.setUserId(existingUser);
        order.setStatus(EnumStatus.PENDING);
        LocalDate shippingDate = request.getShippingDate() == null ? LocalDate.now() : order.getShippingDate();
        if (shippingDate.isBefore(LocalDate.now())) {
            throw new RuntimeException("Date must be at least today !");
        }
        order.setShippingDate(shippingDate);
        order.setDeleted(0);
        return orderMapper.toOrderResponse(
                orderRepository.save(order));
    }

    @Override
    public OrderResponse updateOrder(String orderId, UpdateOrderRequest request) {
        Order existingOrder = orderRepository.findById(orderId).orElseThrow(() -> {
            throw new DataNotFoundException("Order not found !");
        });
        User existingUser = userRepository.findById(request.getUserId()).orElseThrow(() -> {
            throw new DataNotFoundException("User not found !");
        });
        // Xử lý và kiểm tra ngày
        orderMapper.updateOrder(existingOrder, request);
        return orderMapper.toOrderResponse(orderRepository.save(existingOrder));
    }

    @Override
    public List<OrderResponse> getOrderByUserId(String userId) {
        User existingUser = userRepository.findById(userId).orElseThrow(() -> {
            throw new DataNotFoundException("User not found");
        });
        return orderRepository.findOrderByUserId(existingUser).stream().map(
                order -> orderMapper.toOrderResponse(order)).
                toList();
    }

    @Override
    public String deleteOrder(String orderId) {
        Optional<Order> orderOptional = orderRepository.findById(orderId);
        if (orderOptional.isPresent()) {
            Order order = orderOptional.get();
            order.setDeleted(1);
            orderRepository.save(order);
            return "Deleted Order";
        }
        return "Delete order fail";
    }
}
