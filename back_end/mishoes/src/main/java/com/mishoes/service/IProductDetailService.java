package com.mishoes.service;

import com.mishoes.dto.request.create.product.CreateProductDetailRequest;
import com.mishoes.dto.request.update.product.UpdateProductDetailRequest;
import com.mishoes.entity.ProductDetail;
import com.mishoes.dto.response.product.ProductDetailResponse;
import com.mishoes.exception.DataNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface IProductDetailService {
    List<ProductDetail> createProductDetail(CreateProductDetailRequest request) throws DataNotFoundException;

    ProductDetail updateProductDetail(String id, UpdateProductDetailRequest request) throws DataNotFoundException;

    ProductDetail getProductDetail(String id);

    Page<ProductDetailResponse> getProductsDetail(PageRequest pageRequest);

    List<ProductDetailResponse> getProductsDetailByProductId(String productId) throws DataNotFoundException;

    String deleteProductDetail(String id);
}
