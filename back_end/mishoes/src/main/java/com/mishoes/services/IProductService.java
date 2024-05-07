package com.mishoes.services;

import com.mishoes.dtos.ProductDTO;
import com.mishoes.models.Product;

import java.util.List;

public interface IProductService {
    Product getProductById(String id);

    List<Product> getAllProducts();

    Product createProduct(ProductDTO productDTO);

    Product updateProduct(String id,  ProductDTO productDTO);

    String deleteProduct(String  id);
}
