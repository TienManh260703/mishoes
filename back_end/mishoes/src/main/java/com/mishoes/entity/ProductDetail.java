package com.mishoes.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table
@Entity(name = "product_detail")
public class ProductDetail extends BaseEntity {
    @Column(nullable = false,length = 100, unique = true)
    String name;
    Float price;
    Float importPrice;
    Integer quantity;
    String description;
    String notes;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    Product productId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    Category categoryId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "color_id")
    Color colorId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "material_id")
    Material materialId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "size_id")
    Size sizeId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sole_id")
    Sole soleId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id")
    Brand brandId;
}
