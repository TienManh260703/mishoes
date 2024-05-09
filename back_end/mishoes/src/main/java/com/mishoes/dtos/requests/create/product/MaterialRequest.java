package com.mishoes.dtos.requests.create.product;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level =  AccessLevel.PRIVATE)
public class MaterialRequest {
    @NotNull(message = "Material is code cannot be empty")
    String code;
    @NotNull(message = "Material is name cannot be empty")
    String name;
}