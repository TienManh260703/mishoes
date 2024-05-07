package com.mishoes.mappers;

import com.mishoes.dtos.SoleDTO;
import com.mishoes.models.Sole;
import com.mishoes.responses.SoleResponse;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class SoleMapper {
    public Sole toSole(SoleDTO dto) {
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

    public void updateSole(Sole sole, SoleDTO dto) {
        sole.setName(dto.getName());
        sole.setUpdatedAt(LocalDateTime.now());
    }
}
