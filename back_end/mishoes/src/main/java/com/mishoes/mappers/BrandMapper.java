package com.mishoes.mappers;

import com.mishoes.dtos.BrandDTO;
import com.mishoes.models.Brand;
import com.mishoes.responses.BrandResponse;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Getter
@Setter
public class BrandMapper {

    public Brand  toBrand(BrandDTO dto){
        return  Brand.builder()
                .code(dto.getCode())
                .name(dto.getName()).build();
    }
    public BrandResponse toBrandResponse(Brand brand){
        return BrandResponse.builder()
                .id(brand.getId())
                .code(brand.getCode())
                .name(brand.getName())
                .createdAt(brand.getCreatedAt())
                .updatedAt(brand.getUpdatedAt())
                .status(brand.getStatus())
                .build();
    }

    public void updateBrand( Brand brand , BrandDTO dto){
        brand.setName(dto.getName());
        brand.setUpdatedAt(LocalDateTime.now());
    }
}
