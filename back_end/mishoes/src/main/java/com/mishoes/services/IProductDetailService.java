package com.mishoes.services;

import com.mishoes.dtos.requests.create.product.CreateProductDetailRequest;
import com.mishoes.dtos.requests.update.product.UpdateProductDetailRequest;
import com.mishoes.entity.ProductDetail;
import com.mishoes.dtos.responses.product.ProductDetailResponse;
import com.mishoes.exceptions.DataNotFoundException;
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
