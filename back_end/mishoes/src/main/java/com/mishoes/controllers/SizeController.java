package com.mishoes.controllers;

import com.mishoes.dtos.SizeDTO;
import com.mishoes.mappers.SizeMapper;
import com.mishoes.repositories.SizeRepository;
import com.mishoes.services.iplm.SizeService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/sizes")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SizeController {
    SizeService sizeService;
    SizeMapper sizeMapper;

    @PostMapping
    public ResponseEntity<?> createSize(
            @Valid @RequestBody SizeDTO dto) {
        return ResponseEntity.ok().body(
                sizeMapper.toSizeResponse(
                        sizeService.createSize(dto))
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateSize(
            @PathVariable String id,
            @Valid @RequestBody SizeDTO dto) {
        return ResponseEntity.ok().body(
                sizeMapper.toSizeResponse(
                        sizeService.updateSize(id, dto))
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSize(
            @PathVariable String id) {
        return ResponseEntity.ok().body(
                sizeMapper.toSizeResponse(
                        sizeService.getSize(id))
        );
    }

    @GetMapping
    public ResponseEntity<List<?>> getSizes() {
        return ResponseEntity.ok().body(
                sizeService.getSizes().stream()
                        .map(size -> sizeMapper.toSizeResponse(size))
                        .toList()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSize(
            @PathVariable String id) {
        return ResponseEntity.ok().body(
                sizeService.deleteSize(id)
        );
    }
}
