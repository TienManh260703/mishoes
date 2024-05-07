package com.mishoes.mappers;

import com.mishoes.dtos.ColorDTO;
import com.mishoes.models.Color;
import com.mishoes.responses.ColorResponse;
import lombok.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Getter
@Setter
@Builder
public class ColorMapper {
    public Color toColor (ColorDTO dto){
        return Color.builder()
                .code(dto.getCode())
                .name(dto.getName())
                .build();
    }
    public ColorResponse toColorResponse ( Color color){
        return ColorResponse.builder()
                .id(color.getId())
                .code(color.getCode())
                .name(color.getName())
                .createdAt(color.getCreatedAt())
                .updatedAt(color.getUpdatedAt())
                .status(color.getStatus())
                .build();
    }

    public void updateColor (Color color , ColorDTO dto){
        color.setName(dto.getName());
        color.setUpdatedAt(LocalDateTime.now());
    }
}
