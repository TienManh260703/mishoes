package com.mishoes.dtos.requests.create.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryRequest {
    @NotNull(message = "Category is code cannot be empty")
    String code;
    @NotNull(message = "Category is name cannot be empty")
    @NotBlank(message = "Category is name cannot be blank")
    @Size(max = 250, min = 5, message = "Category name must at least 5 character")
    String name;
}
