package com.mishoes.dto.request.create.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mishoes.entity.User;
import com.mishoes.enums.EnumStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level =AccessLevel.PRIVATE )
public class CreateOrderRequest {
    String userId;
    String fullName;
    String phoneNumber;
    String email;
    double totalMoney;
    String note;
    String shippingAddress;
    LocalDate shippingDate;
    Double moneyShip;

    EnumStatus status;
}
