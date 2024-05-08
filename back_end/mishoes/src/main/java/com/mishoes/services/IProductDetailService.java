package com.mishoes.services;

import com.mishoes.dtos.ProductDetailDTO;
import com.mishoes.models.Product;
import com.mishoes.models.ProductDetail;
import com.mishoes.responses.ProductDetailResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface IProductDetailService {
    ProductDetail createProductDetail (ProductDetailDTO dto);
    ProductDetail updateProductDetail (String id, ProductDetailDTO  dto);
    ProductDetail getProductDetail (String id);
    Page<ProductDetailResponse> getProductsDetail (PageRequest pageRequest);
    String deleteProductDetail (String id);
}
