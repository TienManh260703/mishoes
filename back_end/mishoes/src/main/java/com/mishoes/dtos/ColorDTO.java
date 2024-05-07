package com.mishoes.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level =  AccessLevel.PRIVATE)
public class ColorDTO {
    @NotNull(message = "Color is code cannot be empty")
    String code;
    @NotNull(message = "Color is name cannot be empty")
    String name;
}
