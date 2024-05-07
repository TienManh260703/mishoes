package com.mishoes.responses;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class SizeResponse {
    String id;
    String code;
    String name ;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    Integer status;
}
