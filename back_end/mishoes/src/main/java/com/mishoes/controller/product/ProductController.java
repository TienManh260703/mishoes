package com.mishoes.controller.product;

import com.mishoes.dto.request.create.product.ProductRequest;
import com.mishoes.mapper.product.ProductMapper;
import com.mishoes.entity.Product;
import com.mishoes.repository.ProductRepository;
import com.mishoes.service.iplm.product.ProductService;
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

    @GetMapping
    public ResponseEntity<List<?>> getProducts() {
        List<Product> products = productService.getAllProducts();

        return ResponseEntity.ok().body(products.stream().map(
                product ->
                        productMapper.toProductResponse(product)
        ).toList());
    }

    @PostMapping
    public ResponseEntity<?> createProduct(
            @Valid @RequestBody ProductRequest request) {
        return ResponseEntity.ok().body(productMapper.toProductResponse(productService.createProduct(request)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(
            @PathVariable String id, @Valid @RequestBody ProductRequest request) {
        return ResponseEntity.ok().body(productService.updateProduct(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(
            @PathVariable String id) {
        return ResponseEntity.ok().body(productService.deleteProduct(id));
    }
}
