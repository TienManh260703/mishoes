package com.mishoes.controller.order;

import com.mishoes.dto.request.create.order.CreateOrderRequest;
import com.mishoes.dto.request.update.order.UpdateOrderRequest;
import com.mishoes.dto.response.order.OrderResponse;
import com.mishoes.mapper.order.OrderMapper;
import com.mishoes.service.iplm.order.OrderService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/orders")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderController {
    OrderService orderService;
    @GetMapping
    public ResponseEntity<List<?>> getOrders() {
        return ResponseEntity.ok().body(orderService.getOrders());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderById(
            @PathVariable String id) {
        return ResponseEntity.ok().body(
                orderService.getOrder(id)
        );
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getOrdersByUserId(
            @PathVariable String userId) {
        return ResponseEntity.ok().body(
                orderService.getOrderByUserId(userId)
        );
    }

    @PostMapping
    public ResponseEntity<?> createOrder(
            @Valid @RequestBody CreateOrderRequest request) {
        return ResponseEntity.ok().body(orderService.createOrder(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateOrder(
            @PathVariable String id,
            @Valid @RequestBody UpdateOrderRequest request) {
        return ResponseEntity.ok().body(orderService.updateOrder(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable String id) {
        return ResponseEntity.ok().body(
                orderService.deleteOrder(id)
        );
    }
}
