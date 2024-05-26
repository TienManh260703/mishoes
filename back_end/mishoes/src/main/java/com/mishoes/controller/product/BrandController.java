package com.mishoes.controller.product;

import com.mishoes.dto.request.create.product.BrandRequest;
import com.mishoes.mapper.product.BrandMapper;
import com.mishoes.service.iplm.product.BrandService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
                                brand -> brandMapper.toBrandResponse(brand)
                        ).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBrandById(
            @PathVariable String id) {
        return ResponseEntity.ok().body(brandService.getBrandById(id));
    }

    @PostMapping
    public ResponseEntity<?> createBrand(
            @Valid @RequestBody BrandRequest request) {
        return ResponseEntity.ok().body(brandService.createBrand(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateBrand(
            @PathVariable String id,
            @Valid @RequestBody BrandRequest request) {
        return ResponseEntity.ok().body(brandService.updateBrand(id, request));
    }
}
