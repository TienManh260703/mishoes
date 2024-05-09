package com.mishoes.dtos.requests.create.product;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@FieldDefaults(level =  AccessLevel.PRIVATE)
public class SoleRequest {
    @NotNull(message = "Sole is code cannot be empty")
    String code;
    @NotNull(message = "Sole is name cannot be empty")
    String name;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
