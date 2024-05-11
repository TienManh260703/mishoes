package com.mishoes.controller.product;

import com.mishoes.dto.request.create.product.SoleRequest;
import com.mishoes.mapper.product.SoleMapper;
import com.mishoes.service.iplm.product.SoleService;
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
    public ResponseEntity<?> createSole(@Valid @RequestBody SoleRequest request) {
        return ResponseEntity.ok().body(
                soleMapper.toSoleResponse(
                        soleService.createSole(request))
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateSole(@PathVariable String id, @Valid @RequestBody SoleRequest request) {
        return ResponseEntity.ok().body(
                soleMapper.toSoleResponse(
                        soleService.updateSole(id, request))
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
