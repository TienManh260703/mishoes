package com.mishoes.mapper.product;

import com.mishoes.entity.*;
import com.mishoes.dto.response.product.ProductDetailResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductDetailMapper {
    public ProductDetailResponse toProductDetailResponse(ProductDetail productDetail) {
        ProductDetailResponse productDetailResponse = ProductDetailResponse
                .builder()
                .id(productDetail.getId())
                .name(productDetail.getName())
                .price(productDetail.getPrice())
                .importPrice(productDetail.getImportPrice())
                .quantity(productDetail.getQuantity())
                .description(productDetail.getDescription())
                .notes(productDetail.getNotes())
                .productId(productDetail.getProductId().getId())
                .categoryId(productDetail.getCategoryId().getId())
                .colorId(productDetail.getColorId().getId())
                .materialId(productDetail.getMaterialId().getId())
                .sizeId(productDetail.getSizeId().getId())
                .soleId(productDetail.getSoleId().getId())
                .brandId(productDetail.getBrandId().getId())
                .createdAt(productDetail.getCreatedAt())
                .updatedAt(productDetail.getUpdatedAt())
                .status(productDetail.getStatus())
                .build();
        return productDetailResponse;
    }


}
