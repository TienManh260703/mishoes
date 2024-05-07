package com.mishoes.models;

import com.mishoes.models.base.BaseEntity;
import com.mishoes.models.base.EnumStatus;
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
@Table(name = "_order")
@Entity
public class Order extends BaseEntity {
    @Column(length = 50, unique = true, nullable = false)
    String code;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    User userId;
    @Column(name = "full_name", nullable = false)
    String fullName;
    @Column(name = "phone_number", length = 20, nullable = false)
    String phoneNumber;
    @Column(name = "email", length = 100)
    String email;
    @Column(name = "total_money", nullable = false)
    double totalMoney;
    @Column(name = "note", length = 100)
    String note;
    @Column(name = "shipping_address")
    String shippingAddress;
    @Column(name = "shipping_date")
    LocalDate shippingDate;
    @Column(name = "money_ship", nullable = false)
    private Double moneyShip;
    @Column(name = "receipt_date")
    LocalDateTime receiptDate;
    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 50)
    private EnumStatus status;
}