package com.mishoes.controllers;

import com.mishoes.dtos.MaterialDTO;
import com.mishoes.mappers.MaterialMapper;
import com.mishoes.responses.MaterialResponse;
import com.mishoes.services.iplm.MaterialService;
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
    public ResponseEntity<?> createMaterial(@Valid @RequestBody MaterialDTO dto) {
        return ResponseEntity.ok().body(
                materialMapper.toMaterialResponse(
                        materialService.createMaterial(dto)
                )
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<MaterialResponse> updateMaterial(@PathVariable String id,@Valid @RequestBody MaterialDTO dto) {
        return ResponseEntity.ok().body(
                materialMapper.toMaterialResponse(
                        materialService.updateMaterial(id, dto)
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
