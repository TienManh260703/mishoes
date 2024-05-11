package com.mishoes.controller.product;

import com.mishoes.dto.request.create.product.SizeRequest;
import com.mishoes.mapper.product.SizeMapper;
import com.mishoes.service.iplm.product.SizeService;
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
            @Valid @RequestBody SizeRequest request) {
        return ResponseEntity.ok().body(
                sizeMapper.toSizeResponse(
                        sizeService.createSize(request))
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateSize(
            @PathVariable String id,
            @Valid @RequestBody SizeRequest request) {
        return ResponseEntity.ok().body(
                sizeMapper.toSizeResponse(
                        sizeService.updateSize(id, request))
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
