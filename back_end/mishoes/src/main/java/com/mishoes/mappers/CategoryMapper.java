package com.mishoes.mappers;

import com.mishoes.dtos.CategoryDTO;
import com.mishoes.models.Category;
import com.mishoes.responses.CategoryResponse;

import java.time.LocalDateTime;

public class CategoryMapper {
    public Category toCategory (CategoryDTO dto){
        return Category.builder()
                .code(dto.getCode())
                .name(dto.getName())
                .build();
    }

    public CategoryResponse toCategoryResponse (Category category){
        return  CategoryResponse.builder()
                .id(category.getId())
                .code(category.getCode())
                .name(category.getName())
                .createdAt(category.getCreatedAt())
                .updatedAt(category.getUpdatedAt())
                .build();
    }

    public  void  updateCategory (Category category , CategoryDTO dto){
        category.setName(dto.getName());
        category.setUpdatedAt(LocalDateTime.now());
    }
}
