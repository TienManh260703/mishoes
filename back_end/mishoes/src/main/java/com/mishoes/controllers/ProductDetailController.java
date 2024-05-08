package com.mishoes.controllers;

import com.mishoes.dtos.ProductDetailDTO;
import com.mishoes.mappers.ProductDetailMapper;
import com.mishoes.responses.ProductDetailListResponse;
import com.mishoes.responses.ProductDetailResponse;
import com.mishoes.responses.ProductResponse;
import com.mishoes.services.iplm.ProductDetailService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/products-detail")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductDetailController {
    ProductDetailService productDetailService;
    ProductDetailMapper productDetailMapper;

    @PostMapping
    public ResponseEntity<?> createProductDetail(@Valid @RequestBody ProductDetailDTO dto) {
        System.out.println(dto);
        return ResponseEntity.ok().body(
                productDetailMapper.toProductDetailResponse(
                        productDetailService.createProductDetail(dto)
                )
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProductDetail(@PathVariable String id, @Valid @RequestBody ProductDetailDTO dto) {
        return ResponseEntity.ok().body(
                productDetailMapper.toProductDetailResponse(
                        productDetailService.updateProductDetail(id, dto)
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductDetail(@PathVariable String id) {
        return ResponseEntity.ok().body(
                productDetailMapper.toProductDetailResponse(
                        productDetailService.getProductDetail(id)
                )
        );
    }

    @GetMapping
    public ResponseEntity<?> getProductsDetail(
            @RequestParam("page") int page,
            @RequestParam("limit") int limit
    ) {
        PageRequest pageRequest = PageRequest.of(page, limit, Sort.by("createdAt"));
        Page<ProductDetailResponse> productPage = productDetailService.getProductsDetail(pageRequest);
        int totalPage = productPage.getTotalPages();
        List<ProductDetailResponse> products = productPage.getContent();
        return ResponseEntity.ok().body(
                ProductDetailListResponse.builder()
                        .totalPage(totalPage)
                        .productsDetail(products)
                        .build()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProductDetail(@PathVariable String id) {
        return ResponseEntity.ok().body(
                productDetailService.deleteProductDetail(id)
        );
    }
}
