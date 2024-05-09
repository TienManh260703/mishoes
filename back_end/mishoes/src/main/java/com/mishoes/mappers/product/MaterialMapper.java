package com.mishoes.mappers.product;

import com.mishoes.dtos.requests.create.product.MaterialRequest;
import com.mishoes.entity.Material;
import com.mishoes.dtos.responses.product.MaterialResponse;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class MaterialMapper {

    public MaterialResponse toMaterialResponse(Material material) {
        return MaterialResponse.builder()
                .id(material.getId())
                .code(material.getCode())
                .name(material.getName())
                .createdAt(material.getCreatedAt())
                .updatedAt(material.getUpdatedAt())
                .status(material.getStatus())
                .build();
    }

    public Material toMaterial(MaterialRequest dto) {
        return Material
                .builder()
                .code(dto.getCode())
                .name(dto.getName())
                .build();
    }

    public void updateMaterial(Material material, MaterialRequest dto) {
        material.setName(dto.getName());
        material.setUpdatedAt(LocalDateTime.now());
    }
}
