package com.mishoes.responses;

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
}
