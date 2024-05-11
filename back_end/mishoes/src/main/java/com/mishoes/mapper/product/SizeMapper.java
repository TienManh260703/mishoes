package com.mishoes.mapper.product;

import com.mishoes.dto.request.create.product.SizeRequest;
import com.mishoes.entity.Size;
import com.mishoes.dto.response.product.SizeResponse;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class SizeMapper {
    public Size toSize(SizeRequest dto) {
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

    public void updateSize(Size size, SizeRequest dto) {
        size.setName(dto.getName());
        size.setUpdatedAt(LocalDateTime.now());
    }
}

