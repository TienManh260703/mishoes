package com.mishoes.service;

import com.mishoes.entity.Product;

import java.util.List;

public interface IProductService {
    Product getProductById(int id);

    List<Product> getAllProducts();

    Product createProduct(Product product);

    Product updateProduct(Product product);

    void deleteProduct(int id);
}
