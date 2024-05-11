package com.mishoes.mapper.product;

import com.mishoes.dto.request.create.product.CategoryRequest;
import com.mishoes.entity.Category;
import com.mishoes.dto.response.product.CategoryResponse;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CategoryMapper {
    public Category toCategory(CategoryRequest dto) {
        return Category.builder()
                .code(dto.getCode())
                .name(dto.getName())
                .build();
    }

    public CategoryResponse toCategoryResponse(Category category) {
        return CategoryResponse.builder()
                .id(category.getId())
                .code(category.getCode())
                .name(category.getName())
                .createdAt(category.getCreatedAt())
                .updatedAt(category.getUpdatedAt())
                .status(category.getStatus())
                .build();
    }

    public void updateCategory(Category category, CategoryRequest dto) {
        category.setName(dto.getName());
        category.setUpdatedAt(LocalDateTime.now());
    }
}
