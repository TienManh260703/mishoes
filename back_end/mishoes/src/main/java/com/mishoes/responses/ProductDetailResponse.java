package com.mishoes.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductDetailResponse {
    String id;

    String name;
    Float price;
    @JsonProperty(namespace = "import_price")
    Float importPrice;
    @Min(value = 0 , message = "Quantity must br greater than or equal to 0" )
    Integer quantity;
    String description;
    String notes;
    @JsonProperty(namespace ="product_id")
    String productId;
    @JsonProperty(namespace = "category_id")
    String categoryId;
    @JsonProperty(namespace ="color_id")
    String colorId;
    @JsonProperty(namespace = "material_id")
    String materialId;
    @JsonProperty(namespace = "size_id")
    String sizeId;
    @JsonProperty(namespace = "sole_id")
    String soleId;
    @JsonProperty(namespace = "brand_id")
    String brandId;
    @JsonProperty(namespace =  "created_at")
    LocalDateTime createdAt;
    @JsonProperty(namespace = "updated_at")
    LocalDateTime updatedAt;
    Integer status;
}
