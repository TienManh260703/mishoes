package com.mishoes.dtos.requests.create.product;

import com.mishoes.entity.Color;
import com.mishoes.entity.Size;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateProductDetailRequest {
    String productId;
    String brandId;
    String categoryId;
    String materialId;
    String soleId;
    Set<String> sizesId;
    Set<String> colorsId;
    String name;
    @Min(value = 0 , message = "Min")
    Float price;
    @Min(value = 0 , message = "Min")
    Float importPrice;
    @Min(value = 0 , message = "Min")
    Integer quantity;
    String description;
    String notes;
}
