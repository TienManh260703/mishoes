package com.mishoes.dto.response.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mishoes.entity.User;
import com.mishoes.enums.EnumStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderResponse {
    String id;
    @JsonProperty("created_at")
    LocalDateTime createdAt;
    @JsonProperty("updated_at")
    LocalDateTime updatedAt;
    String code;
    @JsonProperty("user")
    String userId;
    @JsonProperty("full_name")
    String fullName;
    @JsonProperty("phone_number")
    String phoneNumber;
    @JsonProperty("email")
    String email;
    @JsonProperty("total_money")
    double totalMoney;
    @JsonProperty("note")
    String note;
    @JsonProperty("shipping_address")
    String shippingAddress;
    @JsonProperty("shipping_date")
    LocalDate shippingDate;
    @JsonProperty("money_ship")
    Double moneyShip;
    @JsonProperty("receipt_date")
    LocalDateTime receiptDate;
    EnumStatus status;
    Integer deleted;
}
