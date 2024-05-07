package com.mishoes.controllers;

import com.mishoes.dtos.CategoryDTO;
import com.mishoes.exceptions.ErrorCode;
import com.mishoes.mappers.CategoryMapper;
import com.mishoes.models.Category;
import com.mishoes.responses.APIResponse;
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
    CategoryMapper categoryMapper;

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> getCategories() {
        return ResponseEntity.ok().body(categoryService.getCategories().stream()
                .map(category ->
                       categoryMapper.toCategoryResponse(category)
                ).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCategory(
            @PathVariable String id) {
        return ResponseEntity.ok().body(APIResponse.<CategoryResponse>builder()
                .code(ErrorCode.SUCCESS.getCode())
                .message(ErrorCode.SUCCESS.getMessage())
                .result(categoryMapper.toCategoryResponse(
                        categoryService.getCategory(id)))
                .build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategory(
            @PathVariable String id,
            @Valid @RequestBody CategoryDTO dto) {
        return ResponseEntity.ok().body(categoryMapper.toCategoryResponse(
                categoryService.updateCategory(id, dto)
        ));
    }

    @PostMapping
    public ResponseEntity<?> createCategory(
            @Valid @RequestBody CategoryDTO dto) {
        return ResponseEntity.ok().body(
                categoryMapper.toCategoryResponse(
                        categoryService.createCategory(dto))
        );
    }

    @DeleteMapping("/{id}")
    public String deleteCategory(
            @PathVariable String id) {
        return categoryService.deleteCategory(id);
    }
}
