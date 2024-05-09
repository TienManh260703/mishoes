package com.mishoes.dtos.responses.product;

import lombok.*;

import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryResponse {
    String id;
    String code;
    String name ;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    Integer status;
}
