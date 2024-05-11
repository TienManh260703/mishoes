package com.mishoes.controller.product;

import com.mishoes.dto.request.create.product.MaterialRequest;
import com.mishoes.mapper.product.MaterialMapper;
import com.mishoes.dto.response.product.MaterialResponse;
import com.mishoes.service.iplm.product.MaterialService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/materials")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MaterialController {
    MaterialService materialService;
    MaterialMapper materialMapper;

    @PostMapping
    public ResponseEntity<?> createMaterial(@Valid @RequestBody MaterialRequest request) {
        return ResponseEntity.ok().body(
                materialMapper.toMaterialResponse(
                        materialService.createMaterial(request)
                )
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<MaterialResponse> updateMaterial(@PathVariable String id,@Valid @RequestBody MaterialRequest request) {
        return ResponseEntity.ok().body(
                materialMapper.toMaterialResponse(
                        materialService.updateMaterial(id, request)
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getMaterial(@PathVariable String id) {
        return ResponseEntity.ok().body(
                materialMapper.toMaterialResponse(
                        materialService.getMaterial(id)
                )
        );
    }

    @GetMapping
    public ResponseEntity<List<?>> getMaterials() {
        return ResponseEntity.ok().body(
                materialService.getMaterials().stream()
                        .map(material ->
                                materialMapper.toMaterialResponse(material))
                        .toList()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMaterial(@PathVariable String id) {
        return ResponseEntity.ok().body(
                materialService.deleteMaterial(id)
        );
    }
}
