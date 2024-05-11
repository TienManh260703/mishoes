package com.mishoes.mapper.product;

import com.mishoes.dto.request.create.product.ProductRequest;
import com.mishoes.entity.Product;
import com.mishoes.dto.response.product.ProductResponse;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ProductMapper {
    public ProductResponse toProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .code(product.getCode())
                .name(product.getName())
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                .status(product.getStatus())
                .build();
    }

    public Product toProduct(ProductRequest productDTO) {
        return Product
                .builder()
                .code(productDTO.getCode())
                .name(productDTO.getName())
                .build();
    }

    public void updateProduct(Product product, ProductRequest productDTO) {
        product.setName(productDTO.getName());
        product.setUpdatedAt(LocalDateTime.now());
    }
}
