package com.mishoes.service;

import com.mishoes.dto.request.create.product.ProductRequest;
import com.mishoes.entity.Product;

import java.util.List;

public interface IProductService {
    Product getProductById(String id);

    List<Product> getAllProducts();

    Product createProduct(ProductRequest productDTO);

    Product updateProduct(String id,  ProductRequest productDTO);

    String deleteProduct(String  id);
}
