package com.mishoes.models;

import com.mishoes.models.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "order_detail")
@Entity
public class OrderDetail extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    Order orderId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_detail_id")
    ProductDetail productDetailId;
    @Column(name = "price",nullable = false)
    private Double price;
    @Column(name = "total_money",nullable = false)
    private Double totalMoney;
    @Column(nullable = false)
    Integer quantity;

}
