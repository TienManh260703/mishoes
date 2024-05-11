package com.mishoes.controller.product;

import com.mishoes.dto.request.create.product.CategoryRequest;
import com.mishoes.exception.ErrorCode;
import com.mishoes.mapper.product.CategoryMapper;
import com.mishoes.dto.response.APIResponse;
import com.mishoes.dto.response.product.CategoryResponse;
import com.mishoes.service.iplm.product.CategoryService;
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
            @Valid @RequestBody CategoryRequest request) {
        return ResponseEntity.ok().body(categoryMapper.toCategoryResponse(
                categoryService.updateCategory(id, request)
        ));
    }

    @PostMapping
    public ResponseEntity<?> createCategory(
            @Valid @RequestBody CategoryRequest request) {
        return ResponseEntity.ok().body(
                categoryMapper.toCategoryResponse(
                        categoryService.createCategory(request))
        );
    }

    @DeleteMapping("/{id}")
    public String deleteCategory(
            @PathVariable String id) {
        return categoryService.deleteCategory(id);
    }
}
