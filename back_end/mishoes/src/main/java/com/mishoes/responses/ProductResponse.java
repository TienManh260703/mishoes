package com.mishoes.responses;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductResponse {
    String id;
    String code;
    String name ;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    Integer status;
}
