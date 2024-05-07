package com.mishoes.mappers;

import com.mishoes.dtos.MaterialDTO;
import com.mishoes.dtos.ProductDTO;
import com.mishoes.models.Material;
import com.mishoes.models.Product;
import com.mishoes.responses.MaterialResponse;
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

    public Material toMaterial(MaterialDTO dto) {
        return Material
                .builder()
                .code(dto.getCode())
                .name(dto.getName())
                .build();
    }

    public void updateMaterial(Material material, MaterialDTO dto) {
        material.setName(dto.getName());
        material.setUpdatedAt(LocalDateTime.now());
    }
}
