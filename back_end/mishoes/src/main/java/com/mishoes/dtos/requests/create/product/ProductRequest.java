package com.mishoes.dtos.requests.create.product;

import jakarta.persistence.Column;
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
@FieldDefaults(level =  AccessLevel.PRIVATE)
public class ProductRequest {
    @NotNull(message = "Product is code cannot be empty")
    String code;
    @NotNull(message = "Product is name cannot be empty")
    String name;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
