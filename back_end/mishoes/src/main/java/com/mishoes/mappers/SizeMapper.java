package com.mishoes.mappers;

import com.mishoes.dtos.SizeDTO;
import com.mishoes.models.Size;
import com.mishoes.responses.SizeResponse;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class SizeMapper {
    public Size toSize(SizeDTO dto) {
        return Size.builder()
                .code(dto.getCode())
                .name(dto.getName())
                .build();
    }

    public SizeResponse toSizeResponse(Size size) {
        return SizeResponse.builder()
                .id(size.getId())
                .code(size.getCode())
                .name(size.getName())
                .createdAt(size.getCreatedAt())
                .updatedAt(size.getUpdatedAt())
                .status(size.getStatus())
                .build();
    }

    public void updateSize(Size size, SizeDTO dto) {
        size.setName(dto.getName());
        size.setUpdatedAt(LocalDateTime.now());
    }
}

