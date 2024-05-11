package com.mishoes.mapper.product;

import com.mishoes.dto.request.create.product.BrandRequest;
import com.mishoes.entity.Brand;
import com.mishoes.dto.response.product.BrandResponse;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Getter
@Setter
public class BrandMapper {

    public Brand  toBrand(BrandRequest dto){
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

    public void updateBrand( Brand brand , BrandRequest dto){
        brand.setName(dto.getName());
        brand.setUpdatedAt(LocalDateTime.now());
    }
}
