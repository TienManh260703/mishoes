package com.mishoes.entity;

import com.mishoes.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "product_images")
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductImage extends BaseEntity {
    public static final Integer MAXIMUM_IMAGES_PER_PRODUCT = 5;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_detail_id")
    ProductDetail productDetailId;
    @Column(name = "image_url", nullable = false, length = 100)
    String imageUrl;
}
