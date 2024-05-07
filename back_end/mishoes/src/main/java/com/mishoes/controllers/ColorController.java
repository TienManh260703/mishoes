package com.mishoes.controllers;

import com.mishoes.dtos.ColorDTO;
import com.mishoes.mappers.ColorMapper;
import com.mishoes.models.Color;
import com.mishoes.services.iplm.ColorService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("${api.prefix}/colors")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ColorController {
    ColorService colorService;
    ColorMapper colorMapper;
    @PostMapping
    public ResponseEntity<?> createColor(@Valid @RequestBody ColorDTO dto) {
        return ResponseEntity.ok().body(
                colorMapper.toColorResponse(
                        colorService.createColor(dto)
                )
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateColor(@PathVariable String id,@Valid @RequestBody ColorDTO dto) {
        return ResponseEntity.ok().body(
                colorService.updateColor(id, dto)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getColor(@PathVariable String id) {
        return ResponseEntity.ok().body(
                colorMapper.toColorResponse(
                        colorService.getColor(id)
                )
        );
    }

    @GetMapping
    public ResponseEntity<?> getColors() {
        return ResponseEntity.ok().body(
                colorService.getColors().stream()
                        .map(color ->
                                colorMapper.toColorResponse(color))
                        .toList()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteColor (@PathVariable String id ){
        return ResponseEntity.ok().body(
          colorService.deletedColor(id)
        );
    }
}
