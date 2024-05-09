package com.mishoes.controllers.product;

import com.mishoes.dtos.requests.create.product.ColorRequest;
import com.mishoes.mappers.product.ColorMapper;
import com.mishoes.services.iplm.product.ColorService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.prefix}/colors")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ColorController {
    ColorService colorService;
    ColorMapper colorMapper;
    @PostMapping
    public ResponseEntity<?> createColor(@Valid @RequestBody ColorRequest request) {
        return ResponseEntity.ok().body(
                colorMapper.toColorResponse(
                        colorService.createColor(request)
                )
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateColor(@PathVariable String id,@Valid @RequestBody ColorRequest request) {
        return ResponseEntity.ok().body(
                colorService.updateColor(id, request)
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
