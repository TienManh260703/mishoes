package com.mishoes.controllers;

import com.mishoes.dtos.BrandDTO;
import com.mishoes.mappers.BrandMapper;
import com.mishoes.models.Brand;
import com.mishoes.models.Product;
import com.mishoes.responses.BrandResponse;
import com.mishoes.services.iplm.BrandService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/brands")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BrandController {
    BrandService brandService;
    BrandMapper brandMapper;

    @GetMapping
    public ResponseEntity<List<?>> findAll() {
        return ResponseEntity.ok().body(
                brandService.getAllBrands().stream()
                        .map(
                                brand -> BrandResponse.builder()
                                        .id(brand.getId())
                                        .code(brand.getCode())
                                        .name(brand.getName())
                                        .createdAt(brand.getCreatedAt())
                                        .updatedAt(brand.getUpdatedAt())
                                        .build()
                        ).toList());

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBrandById(@PathVariable String id) {
        return ResponseEntity.ok().body(brandService.getBrandById(id));
    }

    @PostMapping
    public ResponseEntity<?> createBrand(@Valid @RequestBody BrandDTO brandDTO) {
        return ResponseEntity.ok().body(brandService.createBrand(brandDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateBrand(@PathVariable String id, @Valid @RequestBody BrandDTO brandDTO) {
        return ResponseEntity.ok().body(brandService.updateBrand(id, brandDTO));
    }
}
