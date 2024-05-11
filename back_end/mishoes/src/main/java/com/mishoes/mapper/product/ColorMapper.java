package com.mishoes.mapper.product;

import com.mishoes.dto.request.create.product.ColorRequest;
import com.mishoes.entity.Color;
import com.mishoes.dto.response.product.ColorResponse;
import lombok.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Getter
@Setter
@Builder
public class ColorMapper {
    public Color toColor (ColorRequest dto){
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

    public void updateColor (Color color , ColorRequest dto){
        color.setName(dto.getName());
        color.setUpdatedAt(LocalDateTime.now());
    }
}
