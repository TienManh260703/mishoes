package com.mishoes.mappers;

import com.mishoes.dtos.ProductDTO;
import com.mishoes.models.Product;
import com.mishoes.responses.ProductResponse;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class ProductMapper {
    public ProductResponse toProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .code(product.getCode())
                .name(product.getName())
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                .build();
    }

    public Product toProduct(ProductDTO productDTO) {
        return Product
                .builder()
                .code(productDTO.getCode())
                .name(productDTO.getName())
                .build();
    }

    public void updateProduct(Product product, ProductDTO productDTO) {
        Product
                .builder()
                .name(productDTO.getName())
                .build();
    }
}
