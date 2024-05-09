package com.mishoes.services;

import com.mishoes.dtos.requests.create.product.ProductRequest;
import com.mishoes.entity.Product;

import java.util.List;

public interface IProductService {
    Product getProductById(String id);

    List<Product> getAllProducts();

    Product createProduct(ProductRequest productDTO);

    Product updateProduct(String id,  ProductRequest productDTO);

    String deleteProduct(String  id);
}
