package com.mishoes.dto.request.update.product;


import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateProductDetailRequest {
    String productId;
    String brandId;
    String categoryId;
    String materialId;
    String soleId;
    String sizeId;
    String colorId;
    String name;
    Float price;
    Float importPrice;
    Integer quantity;
    String description;
    String notes;
}
