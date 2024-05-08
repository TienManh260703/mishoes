package com.mishoes.controllers;

import com.mishoes.dtos.SoleDTO;
import com.mishoes.mappers.SoleMapper;
import com.mishoes.services.iplm.SoleService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/soles")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SoleController {
    SoleService soleService;
    SoleMapper soleMapper;

    @PostMapping
    public ResponseEntity<?> createSole(@Valid @RequestBody SoleDTO dto) {
        return ResponseEntity.ok().body(
                soleMapper.toSoleResponse(
                        soleService.createSole(dto))
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateSole(@PathVariable String id, @Valid @RequestBody SoleDTO dto) {
        return ResponseEntity.ok().body(
                soleMapper.toSoleResponse(
                        soleService.updateSole(id, dto))
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSole(@PathVariable String id) {
        return ResponseEntity.ok().body(
                soleMapper.toSoleResponse(
                        soleService.getSole(id)
                )
        );
    }

    @GetMapping
    public ResponseEntity<List<?>> getSoles() {
        return ResponseEntity.ok().body(
                soleService.getSoles().stream()
                        .map(sole -> soleMapper.toSoleResponse(sole))
                        .toList()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSole(@PathVariable String id) {
        return ResponseEntity.ok().body(
                soleService.deleteSoles(id)
        );
    }
}
