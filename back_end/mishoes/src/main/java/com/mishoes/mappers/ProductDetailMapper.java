package com.mishoes.mappers;

import com.mishoes.dtos.ProductDetailDTO;
import com.mishoes.models.*;
import com.mishoes.responses.ProductDetailResponse;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ProductDetailMapper {

    public ProductDetail toProductDetail(ProductDetailDTO dto) {
        Product product = new Product();
        product.setId(dto.getProductId());
        Category category = new Category();
        category.setId(dto.getCategoryId());
        Color color = new Color();
        color.setId(dto.getColorId());
        Material material = new Material();
        material.setId(dto.getMaterialId());
        Size size = new Size();
        size.setId(dto.getSizeId());
        Sole sole = new Sole();
        sole.setId(dto.getSoleId());
        Brand brand = new Brand();
        brand.setId(dto.getBrandId());
        ProductDetail productDetail = ProductDetail
                .builder()
                .name(dto.getName())
                .price(dto.getPrice())
                .importPrice(dto.getImportPrice())
                .quantity(dto.getQuantity())
                .description(dto.getDescription())
                .notes(dto.getNotes())
                .productId(product)
                .categoryId(category)
                .colorId(color)
                .materialId(material)
                .sizeId(size)
                .soleId(sole)
                .brandId(brand)
                .build();
        return productDetail;
    }

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

    public void  updateProductDetail (ProductDetail productDetail , ProductDetailDTO dto){
        Product product = new Product();
        product.setId(dto.getProductId());
        Category category = new Category();
        category.setId(dto.getCategoryId());
        Color color = new Color();
        color.setId(dto.getColorId());
        Material material = new Material();
        material.setId(dto.getMaterialId());
        Size size = new Size();
        size.setId(dto.getSizeId());
        Sole sole = new Sole();
        sole.setId(dto.getSoleId());
        Brand brand = new Brand();
        brand.setId(dto.getBrandId());
        productDetail.setName(dto.getName());
        productDetail.setPrice(dto.getPrice());
        productDetail.setImportPrice(dto.getImportPrice());
        productDetail.setQuantity(dto.getQuantity());
        productDetail.setDescription(dto.getDescription());
        productDetail.setNotes(dto.getNotes());
        productDetail.setProductId(product);
        productDetail.setCategoryId(category);
        productDetail.setColorId(color);
        productDetail.setMaterialId(material);
        productDetail.setSizeId(size);
        productDetail.setSoleId(sole);
        productDetail.setBrandId(brand);
        productDetail.setUpdatedAt(LocalDateTime.now());
    }
}
