package com.mishoes.mapper.order;

import com.mishoes.dto.request.create.order.CreateOrderRequest;
import com.mishoes.dto.request.update.order.UpdateOrderRequest;
import com.mishoes.dto.response.order.OrderResponse;
import com.mishoes.entity.Order;
import com.mishoes.entity.User;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

    public OrderResponse toOrderResponse (Order order){
        return OrderResponse
                .builder()
                .id(order.getId())
                .code(order.getCode())
                .userId(order.getId())
                .fullName(order.getFullName())
                .phoneNumber(order.getPhoneNumber())
                .email(order.getEmail())
                .totalMoney(order.getTotalMoney())
                .note(order.getNote())
                .shippingAddress(order.getShippingAddress())
                .shippingDate(order.getShippingDate())
                .moneyShip(order.getMoneyShip())
                .receiptDate(order.getReceiptDate())
                .status(order.getStatus())
                .deleted(order.getDeleted())
                .createdAt(order.getCreatedAt())
                .updatedAt(order.getUpdatedAt())
                .build();
    }

    public Order createOrder (CreateOrderRequest request){
        return Order.builder()
                .fullName(request.getFullName())
                .phoneNumber(request.getPhoneNumber())
                .email(request.getEmail())
                .totalMoney(request.getTotalMoney())
                .note(request.getNote())
                .shippingAddress(request.getShippingAddress())
                .shippingDate(request.getShippingDate())
                .moneyShip(request.getMoneyShip())
                .build();
    }

    public void  updateOrder (Order order , UpdateOrderRequest request){
        order.setEmail(request.getEmail());
        order.setPhoneNumber(request.getPhoneNumber());
        order.setEmail(request.getEmail());
        order.setTotalMoney(request.getTotalMoney());
        order.setNote(request.getNote());
        order.setShippingDate(request.getShippingDate());
        order.setShippingAddress(request.getShippingAddress());
        order.setMoneyShip(request.getMoneyShip());
        order.setReceiptDate(request.getReceiptDate());
        order.setStatus(request.getStatus());
    }
}
