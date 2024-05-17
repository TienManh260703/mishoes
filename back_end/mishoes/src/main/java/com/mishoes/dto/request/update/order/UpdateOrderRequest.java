package com.mishoes.dto.request.update.order;

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
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateOrderRequest {
    String userId;
    String fullName;
    String phoneNumber;
    String email;
    double totalMoney;
    String note;
    String shippingAddress;
    LocalDate shippingDate;
    Double moneyShip;
    LocalDateTime receiptDate;
    EnumStatus status;
}
