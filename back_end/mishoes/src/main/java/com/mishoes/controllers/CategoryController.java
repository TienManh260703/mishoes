package com.mishoes.controllers;

import com.mishoes.dtos.CategoryDTO;
import com.mishoes.responses.CategoryResponse;
import com.mishoes.services.iplm.CategoryService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/categories")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryController {
    CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> getCategories() {
        return ResponseEntity.ok().body(categoryService.getCategories().stream()
                .map(category ->
                        CategoryResponse.builder()
                                .id(category.getId())
                                .code(category.getCode())
                                .name(category.getName())
                                .updatedAt(category.getUpdatedAt())
                                .createdAt(category.getCreatedAt())
                                .build()
                ).toList());
    }

    @GetMapping("/{/id}")
    public ResponseEntity<?>  getCategory (@PathVariable String id , @Valid @RequestBody CategoryDTO dto){
        return ResponseEntity.ok().body(categoryService.updateCategory(id , dto));
    }
}
