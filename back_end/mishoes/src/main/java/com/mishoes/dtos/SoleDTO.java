package com.mishoes.dtos;

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
public class SoleDTO {
    @NotNull(message = "Sole is code cannot be empty")
    String code;
    @NotNull(message = "Sole is name cannot be empty")
    String name;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
