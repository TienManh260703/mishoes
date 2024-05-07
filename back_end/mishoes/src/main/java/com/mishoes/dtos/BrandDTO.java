package com.mishoes.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level =  AccessLevel.PRIVATE)
public class BrandDTO {
    String code;
    @NotNull(message = "Brand is name cannot be empty")
    String name;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
