package com.mishoes.mappers.product;

import com.mishoes.dtos.requests.create.product.SoleRequest;
import com.mishoes.entity.Sole;
import com.mishoes.dtos.responses.product.SoleResponse;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class SoleMapper {
    public Sole toSole(SoleRequest dto) {
        return Sole.builder()
                .code(dto.getCode())
                .name(dto.getName())
                .build();
    }

    public SoleResponse toSoleResponse(Sole sole) {
        return SoleResponse.builder()
                .id(sole.getId())
                .code(sole.getCode())
                .name(sole.getName())
                .createdAt(sole.getCreatedAt())
                .updatedAt(sole.getUpdatedAt())
                .status(sole.getStatus())
                .build();
    }

    public void updateSole(Sole sole, SoleRequest dto) {
        sole.setName(dto.getName());
        sole.setUpdatedAt(LocalDateTime.now());
    }
}
