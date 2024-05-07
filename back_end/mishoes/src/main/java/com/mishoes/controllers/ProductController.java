package com.mishoes.controllers;

import com.mishoes.dtos.ProductDTO;
import com.mishoes.mappers.ProductMapper;
import com.mishoes.models.Product;
import com.mishoes.repositories.ProductRepository;
import com.mishoes.responses.ProductResponse;
import com.mishoes.services.iplm.ProductService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/products")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductController {
    ProductService productService;
    ProductMapper productMapper;
    private final ProductRepository productRepository;

    @GetMapping
    public ResponseEntity<List<?>> getProducts() {
        List<Product> products = productService.getAllProducts();

        return ResponseEntity.ok().body(products.stream().map(
                        product -> ProductResponse.builder()
                                .id(product.getId())
                                .code(product.getCode())
                                .name(product.getName())
                                .createdAt(product.getCreatedAt())
                                .updatedAt(product.getUpdatedAt())
                                .build())
                .toList()
        );
    }

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@Valid @RequestBody ProductDTO productDTO) {
        return ResponseEntity.ok().body(productMapper.toProductResponse(productService.createProduct(productDTO)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct( @PathVariable String id,@Valid @RequestBody ProductDTO productDTO) {
        return ResponseEntity.ok().body(productService.updateProduct(id, productDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable String id) {
        return ResponseEntity.ok().body(productService.deleteProduct(id));
    }
}
